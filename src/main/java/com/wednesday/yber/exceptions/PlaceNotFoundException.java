package com.wednesday.yber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException(final String message){
        super(message);
    }
}
