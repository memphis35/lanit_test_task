package com.example.lanit.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Person with such id doesn't exist")
public class PersonAlreadyExistException extends RuntimeException {

    public PersonAlreadyExistException() {
        super();
    }

    public PersonAlreadyExistException(String message) {
        super(message);
    }
}
