package tech.silva.linkup.backend.web.dto;

import tech.silva.linkup.backend.entity.Post;

public record PostResponseDto(String user,
                              String description) {
    public static PostResponseDto toResponse(Post post) {
        return new PostResponseDto(
                post.getUser().getUser(),
                post.getDescription()
        );
    }
}
