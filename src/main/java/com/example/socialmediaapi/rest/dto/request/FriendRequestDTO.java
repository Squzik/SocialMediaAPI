package com.example.socialmediaapi.rest.dto.request;

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
@Schema(description = "Входное dto друзей")
public class FriendRequestDTO {

    @Schema(description = "Индифекатор друзей")
    private UUID id;

    @Schema(description = "Отправитель")
    private UserRequestDTO sender;

    @Schema(description = "Получателя")
    private UserRequestDTO receiver;

    @Schema(description = "Принят в друзья")
    private boolean accepted;

}
