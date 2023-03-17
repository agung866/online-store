package com.example.demo.usecase;

import com.example.demo.config.properties.AppProperties;
import com.example.demo.config.properties.ErrProperties;
import com.example.demo.exception.CommonException;
import com.example.demo.model.*;
import com.example.demo.model.response.GenericRs;
import com.example.demo.model.response.Response;
import com.example.demo.model.response.ResponseInfo;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class PurchaseUsecase {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ErrProperties err;
    @Autowired
    AppProperties appProperties;

    public ResponseInfo createOrder(OrderRq orderRq){
        GenericRs body=new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try{
            List<DataProductRs> dataProductRs=productRepository.getAllDataProduct(orderRq.getProductId());
            //If data not found
            if (dataProductRs.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            //if product name is not valid
            if (!orderRq.getProductName().equalsIgnoreCase(dataProductRs.get(0).getNama_product())){
                throw new CommonException("Nama Product yang anda masukan salah,product untuk id " +orderRq.getProductId()+ " adalah "+dataProductRs.get(0).getNama_product());
            }
            //if stok soldout
            if (dataProductRs.get(0).getStok()==0){
                throw new CommonException(err.getSTOCK_EMPTY());
            }
            //if amount is not valid
            if(orderRq.getAmount() != dataProductRs.get(0).getAmount()){
                throw new CommonException("Amount yang masukan salah, silahkan masukan amount "+dataProductRs.get(0).getAmount() );
            }

            if(orderRq.getQuantity()<=0){
                throw new CommonException(err.getQUANTITY_NOT_REQUIRED());
            }
            if(appProperties.getPAYMENT_TYPE().contains(orderRq.getPaymentType())){
                throw new CommonException(err.getPAYMENT_TYPE_NOT_VALID());
            }

            String transactionId=String.format( "%s%s-%d",orderRq.getPaymentType(),orderRq.getProductName(),purchaseRepository.count()+1);
            Date date=new Date();
            PurchaseModel purchaseModel =new PurchaseModel();
            purchaseModel.setTransactionid(transactionId);
            purchaseModel.setProductId(orderRq.getProductId());
            purchaseModel.setProductName(orderRq.getProductName());
            purchaseModel.setAmount(orderRq.getAmount());
            purchaseModel.setQuantity(orderRq.getQuantity());
            purchaseModel.setPurchaseDate(date);
            purchaseModel.setStatusPayment(appProperties.getSTATUS_PAYMENT_CREATED());
            purchaseModel.setStatusOrder(appProperties.getSTATUS_PAYMENT_CREATED());
            purchaseModel.setPaymentType(orderRq.getPaymentType());
            purchaseRepository.createTransaction(purchaseModel);

            Long total = Long.valueOf(orderRq.getQuantity()*orderRq.getAmount());

            OrderRs orderRs =new OrderRs().setTransactionId(transactionId).setTotal(total);

            responseInfo.setSuccess(orderRs);
        }catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo doPayment(String transactionid, int idUser, String paymentType, int amount) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try{
            List<PurchaseModel> order=purchaseRepository.getListTrxByTrxId(transactionid);
            if (order.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            if(order.get(0).getStatusOrder().equalsIgnoreCase(appProperties.getSTATUS_ORDER_CREATED())&&order.get(0).getStatusPayment().equalsIgnoreCase(appProperties.getSTATUS_PAYMENT_CREATED())) {

                List<UserModel> user = userRepository.getAllDataUser(idUser);
                if (user.isEmpty()) {
                    throw new CommonException(err.getUSER_NOT_FOUND());
                }
                if (idUser != user.get(0).getIdUser()) {
                    throw new CommonException(err.getUSER_NOT_VALID());
                }
                if (!user.get(0).getRole().equalsIgnoreCase(appProperties.getROLE_CUSTOMER())) {
                    throw new CommonException(err.getROLE_USER_NOT_VALID());
                }

                if (!paymentType.equalsIgnoreCase(order.get(0).getPaymentType())){
                    throw new CommonException(err.getPAYMENT_NOT_MATCH());
                }
                int total = order.get(0).getAmount() * order.get(0).getQuantity();
                if (amount != total) {
                    throw new CommonException("Jumlah harga yang anda input salah, silahkan input " + total);
                }
                purchaseRepository.updateStatus(transactionid, appProperties.getSTATUS_ORDER_PUBLISH(), appProperties.getSTATUS_PAYMENT_SUCCESS());
            }else{
                throw new CommonException(err.getORDER_NOT_FOUND());
            }
            responseInfo.setSuccess();
        }catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public  ResponseInfo confirmOrder(String transactionId){
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try {
            List<PurchaseModel> order=purchaseRepository.getListTrxByTrxId(transactionId);

            if (order.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }

            if (!order.get(0).getStatusPayment().equalsIgnoreCase(appProperties.getSTATUS_PAYMENT_SUCCESS())){
                throw  new CommonException(err.getPAYMENT_NOT_COMPLETED());
            }

            purchaseRepository.updateStatusOrder(transactionId,appProperties.getSTATUS_ORDER_SUCCESS());
            responseInfo.setSuccess();
        }catch (Exception e){
            responseInfo.setException(e);

        }
        return responseInfo;
    }

}
