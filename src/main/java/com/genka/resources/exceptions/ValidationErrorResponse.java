package com.genka.resources.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ValidationErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timestamp = new Date();
    private Integer code = HttpStatus.UNPROCESSABLE_ENTITY.value();
    private String status = HttpStatus.UNPROCESSABLE_ENTITY.name();
    private String message = "invalid input";
    private List<ValidationErrorDetails> errors;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(List<ValidationErrorDetails> errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationErrorDetails> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorDetails> errors) {
        this.errors = errors;
    }
}
