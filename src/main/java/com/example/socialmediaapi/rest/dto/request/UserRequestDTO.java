package com.example.socialmediaapi.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входное dto юзера")
public class UserRequestDTO {

    @NotBlank(message = "Имя не может быть пустым")
    @Schema(description = "Имя")
    private String name;

    @NotNull(message = "Ошибка! Вы не указали электронную почту")
    @Schema(description = "Электронная почта",
            example = "Test@test.test")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private String mail;

    @NotNull(message = "Ошибка! Вы не указали пароль")
    @Schema(description = "Пароль",
            example = "password1")
    private String password;
}
