package com.example.socialmediaapi.service;

import com.example.socialmediaapi.rest.dto.request.UserRequestDTO;
import com.example.socialmediaapi.rest.dto.response.UserResponseDTO;
import com.example.socialmediaapi.store.entity.User;


import java.util.Set;
import java.util.UUID;

public interface UserService {

    UserResponseDTO getUserById(UUID id);

}
