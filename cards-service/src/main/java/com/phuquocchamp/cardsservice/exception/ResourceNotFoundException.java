package com.phuquocchamp.cardsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String filedName, String fieldValue) {
        super(String.format("%s NOT FOUND WITH GIVEN DATA %s : %s",resourceName,filedName,fieldValue));
    }
    
}
