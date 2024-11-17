package tech.silva.linkup.backend.web.dto;

import tech.silva.linkup.backend.entity.UserEntity;

public record UserProfileResponseDto(Long id,
                                     String user,
                                     String birthDate,
                                     String phone,
                                     String username,
                                     String password) {

    public static UserProfileResponseDto toResponse(UserEntity user) {
        return new UserProfileResponseDto(
                user.getId(),
                user.getUser(),
                user.getBirthDate(),
                user.getPhone(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
