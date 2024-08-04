package com.exadel.financial_service.controller;

import com.exadel.financial_service.model.dto.request.CreationTransferRequestDTO;
import com.exadel.financial_service.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-service")
@RequiredArgsConstructor
@Validated
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid CreationTransferRequestDTO request) {
        transferService.transfer(request);
        return ResponseEntity.ok("Transfer successful");
    }
}
