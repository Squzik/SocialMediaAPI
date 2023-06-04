package com.example.socialmediaapi.service.impl;

import com.example.socialmediaapi.rest.dto.request.FriendRequestCreateDTO;
import com.example.socialmediaapi.rest.dto.request.FriendRequestDTO;
import com.example.socialmediaapi.rest.dto.response.FriendResponseCreateDTO;
import com.example.socialmediaapi.rest.handler.exception.NotFoundException;
import com.example.socialmediaapi.rest.mapper.FriendMapper;
import com.example.socialmediaapi.service.FriendService;
import com.example.socialmediaapi.store.entity.Friend;
import com.example.socialmediaapi.store.entity.FriendRequest;
import com.example.socialmediaapi.store.repository.FriendRepository;
import com.example.socialmediaapi.store.repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class FriendServiceImpl implements FriendService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;
    private final FriendMapper mapper;

    @Override
    public FriendResponseCreateDTO sendFriendRequest(FriendRequestCreateDTO request) {
        FriendRequest friendRequest = friendRequestRepository.saveAndFlush(mapper.toEntity(request));
        return mapper.toCreateResponse(friendRequest);
    }

    @Override
    public FriendRequestDTO acceptFriendRequest(UUID requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Friend request not found"));
        friendRequest.setStatus(true);
        Friend friend1 = new Friend();
        friend1.setUser(friendRequest.getSenderId());
        friend1.setFriend(friendRequest.getReceiverId());
        Friend friend2 = new Friend();
        friend2.setUser(friendRequest.getReceiverId());
        friend2.setFriend(friendRequest.getSenderId());
        friendRepository.saveAll(List.of(friend1, friend2));
        return mapper.toDto(friendRequest);
    }
    @Override
    public void deleteFriendRequest(UUID requestId) {
        friendRequestRepository.deleteById(requestId);
    }
    @Override
    public void deleteFriend(UUID friendId) {
        friendRepository.deleteById(friendId);
    }
}
