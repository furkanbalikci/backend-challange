package com.furkanbalikci.simplecrudapp.exceptions;

import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
