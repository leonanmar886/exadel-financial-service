package com.exadel.financial_service.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepositRequestDTO(
        @NotBlank
        String accountNumber,
        @NotNull
        @Min(value = 1, message = "Amount must be greater than 1")
        Double amount
) {
}
