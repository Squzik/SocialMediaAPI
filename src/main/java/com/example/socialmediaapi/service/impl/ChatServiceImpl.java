
package com.example.socialmediaapi.service.impl;

import com.example.socialmediaapi.service.ChatService;
import com.example.socialmediaapi.store.entity.Chat;
import com.example.socialmediaapi.store.entity.Message;
import com.example.socialmediaapi.store.entity.User;
import com.example.socialmediaapi.store.repository.ChatRepository;
import com.example.socialmediaapi.store.repository.MessageRepository;
import com.example.socialmediaapi.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Override
    public Chat createChat(List<User> users) {
        Chat chat = new Chat();
        chat.setUsers(users);
        chat.setMessages(new ArrayList<>());
        chatRepository.save(chat);
        return chat;
    }
    @Override
    public List<Chat> getChatsForUser(String username) {
        User user = userRepository.findByName(username);
        return chatRepository.findByUsersContains(user);
    }
    @Override
    public List<Message> getMessagesForChat(UUID chatId) {
        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) {
            return new ArrayList<>();
        }
        return messageRepository.findByChat(chat);
    }
    @Override
    public void addMessageToChat(String username, UUID chatId, String text) {
        User user = userRepository.findByName(username);
        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (user != null && chat != null) {
            Message message = new Message();
            message.setUser(user);
            message.setChat(chat);
            message.setText(text);
            messageRepository.save(message);
        }
    }
}

