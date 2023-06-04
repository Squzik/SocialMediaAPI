package com.example.socialmediaapi.security.controllers;

import com.example.socialmediaapi.rest.dto.request.UserRequestDTO;
import com.example.socialmediaapi.rest.dto.response.UserResponseDTO;
import com.example.socialmediaapi.security.jwt.JwtRequest;
import com.example.socialmediaapi.security.jwt.JwtResponse;
import com.example.socialmediaapi.security.jwt.RefreshJwtRequest;
import com.example.socialmediaapi.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "AuthController", description = "Контроллер авторизации и регистрации")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Регистрация нового клиента, с привязкой к нему существующий в базе данных фотографии
     *
     * @param userRequestDTO - данные для регистрации
     * @return информация добавленная в базу данных
     */
    @PostMapping("/registration")
    @Operation(summary = "Добавление клиента")
    public ResponseEntity<UserResponseDTO> createRegistration(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUser(userRequestDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/token")
    @Operation(summary = "Получения нового токена доступа")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        return ResponseEntity.ok(authService.getAccessToken(request.getRefreshToken()));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Получение новых токенов")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        return ResponseEntity.ok(authService.refresh(request.getRefreshToken()));
    }
}