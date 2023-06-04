package com.example.socialmediaapi.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendResponseCreateDTO {


    @Schema(description = "Id в базе данных")
    private UUID id;

    @Schema(description = "Id отправителя в базе данных")
    private UUID senderId;

    @Schema(description = "Id получателя в базе данных")
    private UUID receiverId;

    @Schema(description = "Принят в друзья")
    private boolean accepted;
}
