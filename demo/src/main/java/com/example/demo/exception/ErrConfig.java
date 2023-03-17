package com.example.demo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ErrConfig {

    private HttpStatus httpStatus;
    private String code;
    private String message;

}
