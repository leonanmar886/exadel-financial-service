package com.exadel.authentication_service.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
