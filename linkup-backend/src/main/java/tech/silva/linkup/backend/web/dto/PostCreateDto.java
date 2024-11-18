package tech.silva.linkup.backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateDto(@NotBlank
                            @Size(min = 1, max = 50)
                            String description) {
}
