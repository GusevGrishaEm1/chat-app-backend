package ru.example.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UniqueUsernameException extends Exception {
    public UniqueUsernameException(String message) {
        super(message);
    }
}
