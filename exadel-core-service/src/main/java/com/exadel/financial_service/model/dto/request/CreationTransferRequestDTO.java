package com.exadel.financial_service.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreationTransferRequestDTO(
        @NotBlank
        String payerAccountNumber,
        @NotBlank
        String payeeAccountNumber,
        @NotBlank
        @Min(value = 1, message = "Amount must be greater than 1")
        Double amount
) {
}
