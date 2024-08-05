package com.exadel.financial_service.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DepositRequestDTO(
        @NotNull(message = "Account number cannot be null")
        String accountNumber,
        @NotNull(message = "Amount cannot be null")
        @Min(value = 1, message = "Amount must be greater than 1")
        Double amount
) {
}
