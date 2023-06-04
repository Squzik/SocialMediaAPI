package com.example.socialmediaapi.rest.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public static final String USER_MISMATCH = "Несоответствие авторизированного пользователя";

    public static final String ACCESS_TOKEN_ID_DOESNT_MATCH = "Id клиента не совпадает с id в запросе";

    public static final String SCIENER_INVALID_VALUE = "Неподходящее значение для запроса";

    public static final String DATE_OF_BIRTH_IS_GREATER_THAN_CURRENT_TIME = "Дата рождения больше текущего времени";

}
