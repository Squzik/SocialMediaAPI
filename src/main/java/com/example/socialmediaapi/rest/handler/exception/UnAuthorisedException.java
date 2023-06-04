package com.example.socialmediaapi.rest.handler.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorisedException extends RuntimeException {
    public UnAuthorisedException(String message) {
        super(message);
    }

    public static final String INCORRECT_PASSWORD = "Неправильный пароль";

    public static final String JWT_TOKEN = "Невалидный JWT токен";



}
