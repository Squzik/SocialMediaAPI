package com.example.socialmediaapi.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входное dto поста")
public class PostRequestDTO {

    @NotBlank(message = "Загаловок не может быть пустым")
    @Schema(description = "Загаловок")
    private String title;

    @NotNull(message = "Текст не может быть пустым")
    @Schema(description = "Текст")
    private String content;

    @NotNull(message = "Ошибка! Вы не указали ID пользователя")
    @Schema(description = "ID пользователя")
    private UUID userId;

}
