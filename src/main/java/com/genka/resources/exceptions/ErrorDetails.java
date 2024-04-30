package com.genka.resources.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ErrorDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timestamp = new Date();
    private Integer code;
    private String status;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Integer code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
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
}
