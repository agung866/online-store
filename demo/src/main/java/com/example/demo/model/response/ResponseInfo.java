package com.example.demo.model.response;

import com.example.demo.exception.ApiFault;
import com.example.demo.exception.CommonException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ResponseInfo {
    private HttpStatus httpStatus;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private BaseResponse body;
    private List<ApiFault> faults;

    private  Object Data;

    /**
     * set success without set data
     */
    public void setSuccess() {
        this.httpStatus = HttpStatus.OK;
        body.setSuccess();
    }

    /**
     * set success with data
     * @param data  {@link Object} (generic)
     */
    public void setSuccess(Object data) {
        setSuccess();
        body.setSuccess(data);
    }

    /**
     * set response based on common exception
     * @param e     {@link CommonException}
     */
    public void setCommonException(CommonException e) {
        this.httpStatus = e.getHttpStatus();
        body.setCommonException(e);
        addException(e);
    }

    /**
     * set exception using error message
     * @param errorDescription  error message
     */
    public void setException(String errorDescription) {
        setException(new Exception(errorDescription));
    }

    /**
     * set exception using java exception
     * @param e {@link Exception}
     */
    public void setException(Exception e) {
        if (e instanceof CommonException) {
            setCommonException((CommonException) e);
        } else {
            setCommonException(new CommonException(e));
        }
    }

    /**
     * add exception into list of common exception
     * @param e {@link Exception}
     */
    public void addException(Exception e) {
        addException(new CommonException(e));
    }

    /**
     * add common exception
     * @param e {@link CommonException}
     */
    public void addException(CommonException e) {
        if (this.faults == null) {
            this.faults = new ArrayList<>();
        }
        this.faults.add(e.getApiFault());
    }


    /**
     * get list of exception message
     * @return  {@link List<String>}
     */
    public List<String> getExceptionMessages() {
        return faults.stream()
                .map(ApiFault::error)
                .collect(Collectors.toList());
    }

    /**
     * check is error
     * @return  true/false
     */
    public boolean isError() {
        return faults != null && !faults.isEmpty() && !httpStatus.is2xxSuccessful();
    }

    /**
     * get joined exception message
     * @return  exception message
     */
    public String getError() {
        if (faults != null && !faults.isEmpty()) {
            return StringUtils.join(getExceptionMessages(), ";");
        }
        return "00";
    }

    /**
     * add header
     * @param param     param
     * @param value     value
     */
    public void addHeader(String param, String value) {
        if (this.httpHeaders == null) {
            this.httpHeaders = new HttpHeaders();
        }
        this.httpHeaders.add(param, value);
    }
}
