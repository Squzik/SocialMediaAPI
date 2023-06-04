package com.example.socialmediaapi.store.repository;

import com.example.socialmediaapi.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByName(String username);
    Optional<User> findByMail(String mail);
}
