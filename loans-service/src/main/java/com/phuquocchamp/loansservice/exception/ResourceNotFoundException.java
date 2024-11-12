package com.phuquocchamp.loansservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s NOT FOUND WITH GIVEN DATA %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
