package com.exadel.authentication_service.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String password,
        @NotBlank
        String email,
        @NotBlank
        String cpf
) {
}
