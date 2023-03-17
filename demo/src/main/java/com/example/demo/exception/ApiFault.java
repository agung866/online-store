package com.example.demo.exception;

import com.example.demo.config.constant.CompletionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ApiFault {
    private CompletionStatus status;
    private String code;
    private String type;
    private String message;
    private String detail;
    private Object trace;

    @JsonIgnore
    public String error() {
        return code + ":" + type + ":" + message + ":" + detail;
    }
}