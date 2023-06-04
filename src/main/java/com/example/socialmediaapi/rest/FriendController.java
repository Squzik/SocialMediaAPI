package com.example.socialmediaapi.rest;

import com.example.socialmediaapi.rest.dto.request.FriendRequestCreateDTO;
import com.example.socialmediaapi.rest.dto.request.FriendRequestDTO;
import com.example.socialmediaapi.rest.dto.response.FriendResponseCreateDTO;
import com.example.socialmediaapi.service.FriendService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.example.socialmediaapi.config.OpenApiConfig.SwaggerDependency.SCHEME_NAME;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
@Tag(name = "FriendController", description = "Контроллер постов")
public class FriendController {
    private final FriendService friendService;

    @SecurityRequirement(name = SCHEME_NAME)
    @PostMapping("/requests")
    public ResponseEntity<FriendResponseCreateDTO> sendFriendRequest(@RequestBody FriendRequestCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(friendService.sendFriendRequest(request));
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @PostMapping("/requests/{id}")
    public FriendRequestDTO acceptFriendRequest(@PathVariable UUID id) {
        return friendService.acceptFriendRequest(id);
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable UUID id) {
        friendService.deleteFriendRequest(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable UUID id) {
        friendService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }
}
