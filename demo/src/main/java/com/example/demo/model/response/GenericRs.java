package com.example.demo.model.response;

import com.example.demo.config.constant.CompletionStatus;
import com.example.demo.exception.CommonException;
import com.example.demo.exception.ExceptionInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRs implements BaseResponse {
    private String status;
    private String code;
    private String message;
    private Object data;

    @Override
    public void setSuccess() {
        this.status = "ok";
        this.code = "00";
        this.message = "success";
    }

    @Override
    public void setSuccess(Object data) {
        setSuccess();
        this.data = data;
    }

    @Override
    public void overrideException(ExceptionInfo exception) {
        if(exception.getStatus() != null) {
            this.status = exception.getStatus();
        }
        if(exception.getCode() != null) {
            this.code = exception.getCode();
        }
        if(exception.getMessage() != null) {
            this.message = exception.getMessage();
        }
    }

    public void setCommonException(CommonException commonException) {
        this.code = commonException.getCode();
        this.status = commonException.getStatus().equals(CompletionStatus.BUSINESS_ERROR)?"failed":"error";
        this.message = commonException.getDisplayMessage();
    }

}
