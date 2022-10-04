package com.jchcranelist.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiException {
    int status;
    String message;
    Long timeStamp;
    String path;

    Map<String,String> validationExceptions;

    public ApiException(int status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.path = path;
        this.timeStamp = new Date().getTime();
    }
}
