package com.exadel.financial_service.service.account;

import com.exadel.financial_service.model.dto.request.CreateAccountRequestDTO;
import com.exadel.financial_service.model.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    void createAccount(CreateAccountRequestDTO request);
    Optional<Account> getAccountById(String accountId);
    void deleteAccount(UUID accountId);
    void updateAccount(Account account);
}
