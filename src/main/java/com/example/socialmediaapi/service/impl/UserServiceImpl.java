
package com.example.socialmediaapi.service.impl;

import com.example.socialmediaapi.rest.dto.response.UserResponseDTO;
import com.example.socialmediaapi.rest.handler.exception.NotFoundException;
import com.example.socialmediaapi.rest.mapper.UserMapper;
import com.example.socialmediaapi.service.UserService;
import com.example.socialmediaapi.store.entity.User;
import com.example.socialmediaapi.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.socialmediaapi.rest.handler.exception.NotFoundException.CLIENT_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

/**
     * Получение клиента по его id  базе данных
     *
     * @param userId - id клиента в базе данных
     * @return - response клиента из базы данных
     */

    @Override
    public UserResponseDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new NotFoundException(CLIENT_NOT_FOUND)
                );
        return userMapper.toDTO(user);
    }
}

