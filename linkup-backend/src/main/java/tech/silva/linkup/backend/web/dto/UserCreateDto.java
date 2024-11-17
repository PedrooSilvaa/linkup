package tech.silva.linkup.backend.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDto (@NotBlank
                             String user,
                             @NotBlank
                             String birthDate,
                             @NotBlank
                             String phone,
                             @NotBlank @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
                             String username,
                             @NotBlank @Size(min = 6, max = 10)
                             String password){
}
