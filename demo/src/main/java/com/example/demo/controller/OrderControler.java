package com.example.demo.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CommonException;
import com.example.demo.model.DataProductRs;
import com.example.demo.model.OrderRq;
import com.example.demo.model.response.ResponseInfo;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.usecase.ProductUsecase;
import com.example.demo.usecase.PurchaseUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Validated
public class OrderControler {
    @Autowired
    PurchaseUsecase purchaseUsecase;

    @PostMapping(value = "/createOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder( @RequestBody @Valid OrderRq orderRq
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = purchaseUsecase.createOrder(orderRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doPayment( @RequestParam(name = "transactionid", required = true) String transactionId,
                                        @RequestParam(name = "id_user", required = true) int idUser,
                                        @RequestParam(name = "payment_type", required = true) String paymentType,
                                        @RequestParam(name = "amount", required = true) int amount
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = purchaseUsecase.doPayment(transactionId,idUser,paymentType,amount);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @PostMapping(value = "/confirmorder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmOrder( @RequestParam(name = "transactionid", required = true) String transactionId
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = purchaseUsecase.confirmOrder(transactionId);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
}
