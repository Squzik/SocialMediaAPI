package com.example.socialmediaapi.service;

import com.example.socialmediaapi.rest.dto.request.FriendRequestCreateDTO;
import com.example.socialmediaapi.rest.dto.request.FriendRequestDTO;
import com.example.socialmediaapi.rest.dto.response.FriendResponseCreateDTO;
import com.example.socialmediaapi.store.entity.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FriendService {


    FriendResponseCreateDTO sendFriendRequest(FriendRequestCreateDTO request);

    FriendRequestDTO acceptFriendRequest(UUID requestId);

    void deleteFriendRequest(UUID requestId);

    void deleteFriend(UUID friendId);
}
