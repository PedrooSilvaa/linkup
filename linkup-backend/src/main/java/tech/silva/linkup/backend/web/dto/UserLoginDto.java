package tech.silva.linkup.backend.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto (@NotBlank
                            String user,
                            @NotBlank
                            String password){
}
