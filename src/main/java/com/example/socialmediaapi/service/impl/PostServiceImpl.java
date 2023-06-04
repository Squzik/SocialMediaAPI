package com.example.socialmediaapi.service.impl;


import com.example.socialmediaapi.rest.dto.request.PostRequestDTO;
import com.example.socialmediaapi.rest.dto.response.PostResponseDTO;
import com.example.socialmediaapi.rest.mapper.PostMapper;
import com.example.socialmediaapi.service.PostService;
import com.example.socialmediaapi.store.entity.Friend;
import com.example.socialmediaapi.store.entity.Post;
import com.example.socialmediaapi.store.repository.FriendRepository;
import com.example.socialmediaapi.store.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final FriendRepository friendRepository;

    private final PostRepository postRepository;
    private final PostMapper mapper;


    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        Post post = postRepository.save(mapper.toEntity(postRequestDTO));
        return mapper.toDTO(post);
    }

    @Override
    public List<Post> getLatestPostsFromFriends(UUID user, int page, int size) {
        List<Friend> friends = friendRepository.findByUserId_Id(user);
        List<Post> posts = new ArrayList<>();
        for (Friend friend : friends) {
            List<Post> friendLatestPosts = postRepository.findByAuthorIdOrderByCreatedAtDesc(friend.getFriend(), PageRequest.of(page, size));
            posts.addAll(friendLatestPosts);
        }
        // Для сортировки по времени создания постов
        posts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return posts.stream().skip((long) page * size).limit(size).collect(Collectors.toList());
    }
    @Override
    public PostResponseDTO getPost(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пост " + id + " не найден"));
        return mapper.toDTO(post);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return mapper.toListResponse(posts);
    }

    @Override
    public List<PostResponseDTO> getUserPosts(UUID userId) {
        List<Post> userPosts = postRepository.findByAuthorId_Id(userId);
        return mapper.toListResponse(userPosts);
    }

    @Override
    public PostResponseDTO updatePost(UUID id, PostRequestDTO request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пост " + id + " не найден"));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        postRepository.save(post);
        return mapper.toDTO(post);
    }

    @Override
    public void deletePost(UUID id, UUID authorId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пост " + id + " не найден"));
        if (!post.getAuthorId().getId().equals(authorId)) {
            throw new IllegalArgumentException("Этот пост может быть удалено только автором");
        }
        postRepository.delete(post);
    }

}
