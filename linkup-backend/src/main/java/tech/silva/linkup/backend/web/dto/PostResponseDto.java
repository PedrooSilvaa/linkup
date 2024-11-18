package tech.silva.linkup.backend.web.dto;

import tech.silva.linkup.backend.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

public record PostResponseDto(String user,
                              String description) {
    public static PostResponseDto toResponse(Post post) {
        return new PostResponseDto(
                post.getUser().getUser(),
                post.getDescription()
        );
    }

    public static List<PostResponseDto> toList(List<Post> posts) {
        return posts.stream()
                .map(post -> toResponse(post)).collect(Collectors.toList());
    }
}
