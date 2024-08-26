package com.exadel.financial_service.controller;

import com.exadel.financial_service.model.dto.request.CreateAccountRequestDTO;
import com.exadel.financial_service.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody @Valid CreateAccountRequestDTO request) {
        accountService.createAccount(request);
        return ResponseEntity.ok("Account created");
    }
}
