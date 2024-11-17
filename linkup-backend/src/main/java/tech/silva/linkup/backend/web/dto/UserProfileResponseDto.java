package tech.silva.linkup.backend.web.dto;

public record UserProfileResponseDto(Long id,
                                     String user,
                                     String birthDate,
                                     String phone,
                                     String username,
                                     String password) {
}
