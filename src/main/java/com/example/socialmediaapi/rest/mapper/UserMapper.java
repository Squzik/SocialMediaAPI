package com.example.socialmediaapi.rest.mapper;

import com.example.socialmediaapi.rest.dto.request.UserRequestDTO;
import com.example.socialmediaapi.rest.dto.response.UserResponseDTO;
import com.example.socialmediaapi.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserResponseDTO toDTO(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);

}
