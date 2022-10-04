package com.jchcranelist.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class  ExceptionController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ErrorAttributes errorAttributes;

    public ExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ApiException handleException(WebRequest webRequest){
        Map<String,Object> attributes =  this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.BINDING_ERRORS));
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");
        ApiException exception = new ApiException(status,message,path);
        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrors = (List<FieldError>)attributes.get("errors");
            Map<String ,String> validationExceptions =  new HashMap<>();
            for (FieldError fieldError: fieldErrors){
                validationExceptions.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            exception.setValidationExceptions(validationExceptions);
        }
        logger.error("Status: "+ exception.status +" Message: "+exception.message);
        return exception;
    }

}
