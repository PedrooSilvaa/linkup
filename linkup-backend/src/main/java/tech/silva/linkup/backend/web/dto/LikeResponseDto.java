package tech.silva.linkup.backend.web.dto;

import tech.silva.linkup.backend.entity.Like;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<LikeResponseDto> toList(List<Like> like) {
        return like.stream()
                .map(like1 -> toResponse(like1)).collect(Collectors.toList());
    }
}
