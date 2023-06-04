package com.example.socialmediaapi.rest.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public static final String CLIENT_NOT_FOUND = "Клиент не найден";

    public static final String CLIENT_WITH_CURRENT_EMAIL_NOT_FOUND = "Пользователь с данным Email не найден";

}
