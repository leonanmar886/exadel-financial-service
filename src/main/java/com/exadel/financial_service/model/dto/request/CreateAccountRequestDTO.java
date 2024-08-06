package com.exadel.financial_service.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequestDTO(
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String cpf
) {
}