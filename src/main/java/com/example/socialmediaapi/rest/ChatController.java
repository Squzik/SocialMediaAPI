package com.example.socialmediaapi.rest;


import com.example.socialmediaapi.service.ChatService;
import com.example.socialmediaapi.store.entity.Chat;
import com.example.socialmediaapi.store.entity.Message;
import com.example.socialmediaapi.store.entity.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.example.socialmediaapi.config.OpenApiConfig.SwaggerDependency.SCHEME_NAME;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@Tag(name = "chatController", description = "Контроллер чата")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/create")
    @SecurityRequirement(name = SCHEME_NAME)
    public ResponseEntity<Chat> createChat(@RequestBody List<User> users) {
        Chat chat = chatService.createChat(users);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/{username}")
    @SecurityRequirement(name = SCHEME_NAME)
    public ResponseEntity<List<Chat>> getChatsForUser(@PathVariable String username) {
        List<Chat> chats = chatService.getChatsForUser(username);
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/{chatId}/messages")
    @SecurityRequirement(name = SCHEME_NAME)
    public ResponseEntity<List<Message>> getMessagesForChat(@PathVariable UUID chatId) {
        List<Message> messages = chatService.getMessagesForChat(chatId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{chatId}/messages")
    @SecurityRequirement(name = SCHEME_NAME)
    public ResponseEntity<Void> addMessageToChat(@PathVariable UUID chatId,
                                              @RequestParam String username,
                                              @RequestParam String text) {
        chatService.addMessageToChat(username, chatId, text);
        return ResponseEntity.ok().build();
    }
}
