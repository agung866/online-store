package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class PurchaseModel {

    String transactionid;
    int productId;
    String productName;
    int amount;

    int quantity;
    Date purchaseDate;
    String statusPayment;
    String statusOrder;
    String paymentType;

    String idUser;
}
