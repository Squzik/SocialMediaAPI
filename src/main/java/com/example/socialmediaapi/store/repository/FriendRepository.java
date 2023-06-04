package com.example.socialmediaapi.store.repository;

import com.example.socialmediaapi.store.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UUID> {

    List<Friend> findByUserId_Id(UUID userId);
}