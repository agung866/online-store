package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderRq {
    int productId;
    String productName;
    int amount;
    int quantity;
    String paymentType;
}
