package com.exadel.financial_service.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequestDTO(
        @NotNull(message = "Name cannot be null")
        String name,
        @NotNull(message = "'Email cannot be null'")
        @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
        String email,
        @NotNull(message = "CPF cannot be null")
        String cpf
) {
}