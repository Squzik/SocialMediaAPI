package com.example.socialmediaapi.store.repository;

import com.example.socialmediaapi.store.entity.Chat;
import com.example.socialmediaapi.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

    List<Chat> findByUsersContains(User user);
}