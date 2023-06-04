package com.example.socialmediaapi.rest.mapper;

import com.example.socialmediaapi.rest.dto.request.FriendRequestCreateDTO;
import com.example.socialmediaapi.rest.dto.request.FriendRequestDTO;
import com.example.socialmediaapi.rest.dto.response.FriendResponseCreateDTO;
import com.example.socialmediaapi.store.entity.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FriendMapper {


    FriendRequestDTO toDto(FriendRequest entity);
    @Mapping(target = "senderId.", source = "senderId.id")
    @Mapping(target = "receiverId", source = "receiverId.id")
    FriendResponseCreateDTO toCreateResponse(FriendRequest entity);

    @Mapping(target = "senderId.id", source = "senderId")
    @Mapping(target = "receiverId.id", source = "receiverId")
    FriendRequest toEntity(FriendRequestCreateDTO request);
}
