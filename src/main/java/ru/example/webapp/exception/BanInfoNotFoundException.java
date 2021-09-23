package ru.example.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BanInfoNotFoundException extends Exception{
    public BanInfoNotFoundException(String message) {
        super(message);
    }
}
