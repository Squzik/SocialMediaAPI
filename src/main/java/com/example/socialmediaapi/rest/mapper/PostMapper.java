package com.example.socialmediaapi.rest.mapper;

import com.example.socialmediaapi.rest.dto.request.PostRequestDTO;
import com.example.socialmediaapi.rest.dto.response.PostResponseDTO;
import com.example.socialmediaapi.store.entity.Post;
import com.example.socialmediaapi.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring"
)
public interface PostMapper {
    @Mapping(target = "authorId", source = "id")
    PostResponseDTO toDTO(Post post);

    @Mapping(target = "authorId", source = "id")
    List<PostResponseDTO> toListResponse(List<Post> post);

    @Mapping(target = "authorId.id", source = "userId")
    Post toEntity(PostRequestDTO postRequestDTO);

}
