package com.example.demo.model.response;


import com.example.demo.exception.CommonException;
import com.example.demo.exception.ExceptionInfo;

/**
* base response
*/
public interface BaseResponse {
    void setSuccess();
    void setSuccess(Object data);
    void overrideException(ExceptionInfo exception);
    void setCommonException(CommonException commonException);
}