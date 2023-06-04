package com.example.socialmediaapi.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входное dto создание друзей")
public class FriendRequestCreateDTO {

    @NotBlank(message = "Id отправителя не может быть пустым")
    @Schema(description = "Id отправителя")
    private UUID senderId;

    @NotBlank(message = "Id получателя не может быть пустым")
    @Schema(description = "Id получателя")
    private UUID receiverId;
}
