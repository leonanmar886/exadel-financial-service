package com.exadel.financial_service.controller;

import com.exadel.financial_service.model.dto.request.CreationTransferRequestDTO;
import com.exadel.financial_service.model.dto.request.DepositRequestDTO;
import com.exadel.financial_service.service.core.CoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial-service")
@RequiredArgsConstructor
@Validated
public class FinancialOperationsController {
    private final CoreService coreService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid CreationTransferRequestDTO request) {
        coreService.transfer(
            request.payerAccountNumber(),
            request.payeeAccountNumber(),
            request.amount()
        );
        return ResponseEntity.ok("Transfer successful");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody @Valid DepositRequestDTO request) {
        coreService.deposit(
            request.accountNumber(),
            request.amount()
        );
        return ResponseEntity.ok("Deposit successful");
    }
}
