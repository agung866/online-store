package com.example.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Configuration
public class AppProperties {

    private String STATUS_ORDER_CREATED="CREATED";
    private String STATUS_PAYMENT_CREATED="CREATED";
    private String STATUS_ORDER_PUBLISH="PUBLISH";
    private String STATUS_PAYMENT_SUCCESS="SUCCESS";
    private String STATUS_ORDER_SUCCESS="SUCCESS";

    private String ROLE_ADMIN="ADMIN";
    private String ROLE_CUSTOMER="CUSTOMER";

    private List<String> PAYMENT_TYPE=Arrays.asList("OVO,DANA,GOPAY");

    private boolean TAMBAH_STOK=true;
}
