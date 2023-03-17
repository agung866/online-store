package com.example.demo.usecase;

import com.example.demo.config.properties.AppProperties;
import com.example.demo.config.properties.ErrProperties;
import com.example.demo.exception.CommonException;
import com.example.demo.model.DataProductRq;
import com.example.demo.model.DataProductRs;
import com.example.demo.model.response.GenericRs;
import com.example.demo.model.response.ResponseInfo;
import com.example.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductUsecase {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ErrProperties err;
    @Autowired
    AppProperties appProperties;
    public ResponseInfo getDataProduct(int id){
        GenericRs body=new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);

        try {
            List<DataProductRs> dataProductRq=productRepository.getAllDataProduct(id);

            if(dataProductRq.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            responseInfo.setSuccess(dataProductRq);
        }catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo deleteProduct(int id){
        GenericRs body=new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);

        try {
            List<DataProductRs> dataProductRq=productRepository.getAllDataProduct(id);

            if(dataProductRq.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            productRepository.delete(id);
            responseInfo.setSuccess(dataProductRq);
        }catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }
    public ResponseInfo updateHargaProduct(int id,int amount){
        GenericRs body=new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);

        try {
            List<DataProductRs> dataProductRq=productRepository.getAllDataProduct(id);

            if(dataProductRq.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            productRepository.updateHarga(id,amount);

            if(dataProductRq.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            responseInfo.setSuccess();
        }catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo updateStokProduct(int id,int stok,boolean tambah){
        GenericRs body=new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        tambah=true;

        try {
            List<DataProductRs> dataProductRs=productRepository.getAllDataProduct(id);

            if(dataProductRs.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            int stokTotal=0;
            if(tambah){
                stokTotal=stok + dataProductRs.get(0).getStok();
            }else{
                stokTotal=stok - dataProductRs.get(0).getStok();
            }



            productRepository.updateStok(id,stokTotal);
            responseInfo.setSuccess();
        }catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo insertNewProduct(DataProductRq dataProductRq) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try {
            productRepository.insertProduct(dataProductRq);
            responseInfo.setSuccess();
        } catch (Exception e) {
            responseInfo.setException(e);
        }

        return responseInfo;
    }


}
