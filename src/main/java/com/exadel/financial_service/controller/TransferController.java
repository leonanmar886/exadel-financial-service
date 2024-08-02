package com.exadel.financial_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial-service")
public class TransferController {
    @GetMapping("/transfer")
    public ResponseEntity<String> transfer() {
         return ResponseEntity.ok("Transfer successful");
    }
}
