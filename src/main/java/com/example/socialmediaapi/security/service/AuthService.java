package com.example.socialmediaapi.security.service;

import com.example.socialmediaapi.rest.dto.request.UserRequestDTO;
import com.example.socialmediaapi.rest.dto.response.UserResponseDTO;
import com.example.socialmediaapi.rest.handler.exception.NotFoundException;
import com.example.socialmediaapi.rest.handler.exception.UnAuthorisedException;
import com.example.socialmediaapi.rest.mapper.UserMapper;
import com.example.socialmediaapi.security.jwt.JwtRequest;
import com.example.socialmediaapi.security.jwt.JwtResponse;
import com.example.socialmediaapi.store.entity.User;
import com.example.socialmediaapi.store.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public JwtResponse login(@NonNull JwtRequest authRequest) {
        User user = userRepository.findByMail(authRequest.getLogin())
                .orElseThrow(
                        () -> new NotFoundException(NotFoundException.CLIENT_WITH_CURRENT_EMAIL_NOT_FOUND)
                );

        if (passwordEncoder.matches(String.valueOf(authRequest.getPassword()), user.getPassword())) {

            final String accessToken = jwtProvider.generateAccessToken(user.getMail(), user.getId());
            final String refreshToken = jwtProvider.generateRefreshToken(user.getMail());
            refreshStorage.put(user.getMail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken, user.getId());
        } else {
            throw new UnAuthorisedException(UnAuthorisedException.INCORRECT_PASSWORD);
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final String email = jwtProvider.getRefreshClaims(refreshToken)
                    .getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            User user = userRepository.findByMail(email)
                    .orElseThrow(
                            () -> new NotFoundException(NotFoundException.CLIENT_WITH_CURRENT_EMAIL_NOT_FOUND)
                    );
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final String accessToken = jwtProvider.generateAccessToken(email, user.getId());
                return new JwtResponse(accessToken, null, user.getId());
            }
        }
        throw new UnAuthorisedException(UnAuthorisedException.JWT_TOKEN);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        JwtResponse response = getAccessToken(refreshToken);
        final String email = jwtProvider.getRefreshClaims(refreshToken).getSubject();
        final String newRefreshToken = jwtProvider.generateRefreshToken(email);
        refreshStorage.put(email, newRefreshToken);
        response.setRefreshToken(newRefreshToken);
        return response;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userRepository.saveAndFlush(user);
        return userMapper.toDTO(user);
    }



}
