package tech.silva.linkup.backend.web.dto;

import tech.silva.linkup.backend.entity.Like;

public record LikeResponseDto(Long id,
                              UserProfileResponseDto user,
                              PostResponseDto post) {
    public static LikeResponseDto toResponse(Like like) {
        return new LikeResponseDto(
                like.getId(),
                UserProfileResponseDto.toResponse(like.getUser()),
                PostResponseDto.toResponse(like.getPost())
        );
    }
}
