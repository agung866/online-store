package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderRs {
    String transactionId;
    Long total;
}
