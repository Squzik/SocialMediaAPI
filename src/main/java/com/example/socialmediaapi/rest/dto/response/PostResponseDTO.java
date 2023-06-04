package com.example.socialmediaapi.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

    @Schema(description = "Id в базе данных")
    private UUID id;

    @Schema(description = "Загаловок")
    private String title;

    @Schema(description = "Текст")
    private String content;

    @Schema(description = "ID пользователя")
    private UUID authorId;

    @Schema(description = "Время создания")
    private LocalDateTime createdAt;

}
