package ru.example.webapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.example.webapp.exception.*;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public Object userNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest) {
        return response(HttpStatus.NOT_FOUND, userNotFoundException, webRequest);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public Object roomNotFoundException(RoomNotFoundException roomNotFoundException, WebRequest webRequest) {
        return response(HttpStatus.NOT_FOUND, roomNotFoundException, webRequest);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public Object messageNotFoundException(MessageNotFoundException messageNotFoundException, WebRequest webRequest) {
        return response(HttpStatus.NOT_FOUND, messageNotFoundException, webRequest);
    }

    @ExceptionHandler(UserInRoomNotFoundException.class)
    public Object userInRoomNotFoundException(UserInRoomNotFoundException userInRoomNotFoundException, WebRequest webRequest) {
        return response(HttpStatus.NOT_FOUND, userInRoomNotFoundException, webRequest);
    }

    @ExceptionHandler(BanInfoNotFoundException.class)
    public Object banInfoNotFoundException(BanInfoNotFoundException banInfoNotFoundException, WebRequest webRequest) {
        return response(HttpStatus.NOT_FOUND, banInfoNotFoundException, webRequest);
    }

    @ExceptionHandler(DiscInfoNotFoundException.class)
    public Object discInfoNotFoundException(DiscInfoNotFoundException discInfoNotFoundException, WebRequest webRequest) {
        return response(HttpStatus.NOT_FOUND, discInfoNotFoundException, webRequest);
    }

    @ExceptionHandler(UserAccessException.class)
    public Object userAccessException(UserAccessException userAccessException, WebRequest webRequest) {
        return response(HttpStatus.INTERNAL_SERVER_ERROR, userAccessException, webRequest);
    }

    @ExceptionHandler(UniqueUsernameException.class)
    public Object uniqueUsernameException(UniqueUsernameException uniqueUsernameException, WebRequest webRequest) {
        return response(HttpStatus.INTERNAL_SERVER_ERROR, uniqueUsernameException, webRequest);
    }

    private Object response(HttpStatus httpStatus, Exception exception, WebRequest webRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", httpStatus.toString());
        body.put("message", exception.getMessage());
        return  new ResponseEntity<>(body, headers, httpStatus);
    }


}
