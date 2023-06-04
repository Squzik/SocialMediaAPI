package com.example.socialmediaapi.rest.handler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ValidationErrorValue {

    private final String fieldName;

    private final String message;

}
