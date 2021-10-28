package com.example.lanit.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid car object")
public class CarCantBeSavedException extends RuntimeException {
    public CarCantBeSavedException(String message) {
        super(message);
    }
}
