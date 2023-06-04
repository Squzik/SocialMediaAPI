package com.example.socialmediaapi.store.repository;

import com.example.socialmediaapi.store.entity.Chat;
import com.example.socialmediaapi.store.entity.Friend;
import com.example.socialmediaapi.store.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByChat(Chat chat);
}