package com.example.socialmediaapi.store.repository;

import com.example.socialmediaapi.store.entity.Friend;
import com.example.socialmediaapi.store.entity.Post;
import com.example.socialmediaapi.store.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByAuthorId_Id(UUID authorId);
    List<Post> findByAuthorIdOrderByCreatedAtDesc(User user, Pageable pageable);

}
