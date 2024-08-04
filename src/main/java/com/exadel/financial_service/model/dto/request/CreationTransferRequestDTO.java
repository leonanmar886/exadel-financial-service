package com.exadel.financial_service.model.dto.request;

import com.exadel.financial_service.model.entity.Transfer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreationTransferRequestDTO(
        @NotNull(message = "Payer account number cannot be null")
        String payerAccountNumber,
        @NotNull(message = "Payee account number cannot be null")
        String payeeAccountNumber,
        @NotNull(message = "Amount cannot be null")
        @Min(value = 1, message = "Amount must be greater than 1")
        Double amount
) {
}
