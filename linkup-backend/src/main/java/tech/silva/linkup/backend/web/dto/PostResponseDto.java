package tech.silva.linkup.backend.web.dto;

import tech.silva.linkup.backend.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

public record PostResponseDto(Long id,
                              String user,
                              String description,
                              boolean status,
                              Long countLike) {

    public static PostResponseDto toResponse(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getUser().getUser(),
                post.getDescription(),
                false,
                0L
        );
    }

    public static List<PostResponseDto> toList(List<Post> posts) {
        return posts.stream()
                .map(post -> toResponse(post)).collect(Collectors.toList());
    }
}
