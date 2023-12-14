package com.eazybytes.accounts.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class CustomerAleareadyExisted extends RuntimeException{
    public CustomerAleareadyExisted(String message) {
        super(message);
    }
}
