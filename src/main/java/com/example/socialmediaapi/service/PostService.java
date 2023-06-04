package com.example.socialmediaapi.service;

import com.example.socialmediaapi.rest.dto.request.PostRequestDTO;
import com.example.socialmediaapi.rest.dto.response.PostResponseDTO;
import com.example.socialmediaapi.store.entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostResponseDTO createPost(PostRequestDTO postRequestDTO);

    List<Post> getLatestPostsFromFriends(UUID user, int page, int size);

    PostResponseDTO getPost(UUID id);

    List<PostResponseDTO> getAllPosts();

    List<PostResponseDTO> getUserPosts(UUID userId);

    PostResponseDTO updatePost(UUID id, PostRequestDTO request);

    void deletePost(UUID id, UUID authorId);
}
