package com.example.demo.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String code;
    private String status;
    private String message;
    private T data;

    @JsonIgnore
    public boolean isOk() { return status.equals(Status.ok); }

    @JsonIgnore
    public Response<T> defaultSuccess() {
        this.setCode("00").setMessage("Success").setStatus("ok");
        return this;
    }
}
