package ru.example.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserInRoomNotFoundException extends Exception {
    public UserInRoomNotFoundException(String message) {
        super(message);
    }
}
