package com.example.lanit.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Person with such id doesn't exist")
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super();
    }
}
