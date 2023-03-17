package com.example.demo.exception;


import com.example.demo.config.constant.*;
import com.example.demo.utils.StringTools;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommonException extends Exception {
    private CompletionStatus status;
    private HttpStatus httpStatus;
    private String code;
    private String type;
    private String displayMessage;
    private Exception originException;

    public CommonException(HttpStatus status, String code, String type, String displayMessage, String message) {
        super(message);
        this.httpStatus = status;
        this.status = status.is5xxServerError()?CompletionStatus.SYSTEM_ERROR:CompletionStatus.BUSINESS_ERROR;
        this.code = code;
        this.type = type;
        this.displayMessage = displayMessage;
    }

    public CommonException(Exception e) {
        super(e.getMessage());
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.status = CompletionStatus.SYSTEM_ERROR;
        this.code = "99";
        this.type = e.getClass().getSimpleName();
        this.displayMessage = "Unknown Error: " + e.getClass().getSimpleName();
        this.originException = e;
    }
    public CommonException(String exceptionEnum) {
        super(String.format("ErrorConfig -> %s", exceptionEnum));
        ErrConfig errConfig = StringTools.errorConfig(exceptionEnum);
        if (!StringTools.isEmptyOrNull(errConfig.getCode())) {
            this.code = errConfig.getCode();
            this.status = errConfig.getHttpStatus().is5xxServerError() ? CompletionStatus.SYSTEM_ERROR : CompletionStatus.BUSINESS_ERROR;
            this.type = "BadRequest";
            this.httpStatus = errConfig.getHttpStatus();
            this.displayMessage = errConfig.getMessage();
        } else {
            this.code = "01";
            this.status = CompletionStatus.BUSINESS_ERROR;
            this.type = "BadRequest";
            this.httpStatus = HttpStatus.BAD_REQUEST;
            this.displayMessage = exceptionEnum;
        }

    }


    public ApiFault getApiFault() {
        return new ApiFault()
            .setStatus(status)
            .setCode(code)
            .setType(type)
            .setMessage(displayMessage)
            .setDetail(type + ":" + super.getMessage());
    }
}