package com.example.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("error")
public class ErrProperties {

    private String DATA_NOT_FOUND = "404|01|Data Not Found";
    private String INSERT_FAILED = "404|02|insert data failed,try again";
    private String STOCK_EMPTY = "404|03|STOCK is EMPTY";
    private String QUANTITY_NOT_REQUIRED= "404|04|Please Input min. 1 quantity";
    private String USER_NOT_FOUND= "404|05|DATA USER NOT FOUND";
    private String USER_NOT_VALID= "404|06|DATA USER VALID";
    private String ROLE_USER_NOT_VALID= "404|07|ROLE USER VALID";
    private String PAYMENT_NOT_MATCH="404|08|PAYMENT TYPE NOT MATCH";
    private String PAYMENT_TYPE_NOT_VALID="404|09|PAYMENT TYPE NOT VALID, USE OVO, DANA AND GOPAY";

    private String ORDER_NOT_FOUND = "404|10|ORDER Not Found";

    private String PAYMENT_NOT_COMPLETED = "404|11|PAYMENT NOT COMPLETE, PLEASE COMPLETE THE PAYMENT FIRTS";
    private String USERNAME_NOT_FOUND = "404|12|Username Not Found";
    private String PASSWORD_NOTMATCH = "404|13|password Not Match";
//    private String DATA_NOT_FOUND = "404|01|Data Not Found";
}
