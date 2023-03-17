package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class DataProductRq {
    String nama_product;
    int amount;
    int stok;
    String description;
    Date created_date;
    Date last_update;
    String supplier;
}

