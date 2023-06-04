package com.example.socialmediaapi.service;

import com.example.socialmediaapi.rest.dto.response.UserResponseDTO;
import com.example.socialmediaapi.store.entity.Chat;
import com.example.socialmediaapi.store.entity.Message;
import com.example.socialmediaapi.store.entity.User;

import java.util.List;
import java.util.UUID;

public interface ChatService {


    Chat createChat(List<User> users);

    List<Chat> getChatsForUser(String username);

    List<Message> getMessagesForChat(UUID chatId);

    void addMessageToChat(String username, UUID chatId, String text);
}
