package com.reserveall.reserveall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenericException extends RuntimeException{
    public GenericException(String message){
        super(message);
    }
}
