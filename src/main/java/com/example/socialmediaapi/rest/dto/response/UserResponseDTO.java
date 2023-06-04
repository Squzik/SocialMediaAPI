package com.example.socialmediaapi.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    @Schema(description = "Id в базе данных")
    private UUID id;

    @Schema(description = "Имя")
    private String name;

    @Schema(
            description = "Электронная почта",
            example = "Test@test.test"
    )
    private String mail;

}