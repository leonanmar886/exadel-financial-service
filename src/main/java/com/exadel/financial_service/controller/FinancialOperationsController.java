package com.exadel.financial_service.controller;

import com.exadel.financial_service.model.dto.request.CreationTransferRequestDTO;
import com.exadel.financial_service.service.financial_operation.FinancialOperationsService;
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
    private final FinancialOperationsService financialOperationsService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid CreationTransferRequestDTO request) {
        financialOperationsService.transfer(request);
        return ResponseEntity.ok("Transfer successful");
    }
}
