package com.example.socialmediaapi.rest;

import com.example.socialmediaapi.rest.dto.request.PostRequestDTO;
import com.example.socialmediaapi.rest.dto.response.PostResponseDTO;
import com.example.socialmediaapi.service.PostService;
import com.example.socialmediaapi.store.entity.Post;
import com.example.socialmediaapi.store.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.example.socialmediaapi.config.OpenApiConfig.SwaggerDependency.SCHEME_NAME;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Tag(name = "PostController", description = "Контроллер постов")
public class PostController {

    private final PostService postService;

    @SecurityRequirement(name = SCHEME_NAME)
    @PostMapping
    @Operation(summary = "Добавление поста")
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postRequestDTO));
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @GetMapping("/{id}")
    @Operation(summary = "Получение поста по id")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable(value = "id") UUID postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPost(postId));
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @GetMapping("userPosts/{id}")
    @Operation(summary = "Получение поста пользователя по id")
    public ResponseEntity<List<PostResponseDTO>> getUserPostsById(@PathVariable(value = "id") UUID userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getUserPosts(userId));
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @GetMapping("all")
    @Operation(summary = "Получение постов")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(postService.getAllPosts());
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @PutMapping("/{id}")
    @Operation(summary = "Обновление поста")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable(value = "id") UUID postId, @Valid @RequestBody PostRequestDTO postRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.updatePost(postId, postRequestDTO));
    }

    @SecurityRequirement(name = SCHEME_NAME)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поста")
    public ResponseEntity<Void> deletePost(@PathVariable(value = "id") UUID postId,@PathVariable(value = "id")  UUID authorId) {
        postService.deletePost(postId,authorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friends/{id}")
    @SecurityRequirement(name = SCHEME_NAME)
    @Operation(summary = "Получение последнего поста от друга")
    public String getLatestPostsFromFriends(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @PathVariable(value = "id")  UUID userId,
                                            Model model) {

        List<Post> friendPosts = postService.getLatestPostsFromFriends(userId, page, size);
        model.addAttribute("friendPosts", friendPosts);
        return "friend_posts";
    }

}
