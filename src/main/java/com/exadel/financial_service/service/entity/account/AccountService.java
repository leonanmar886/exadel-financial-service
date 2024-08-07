package com.exadel.financial_service.service.entity.account;

import com.exadel.financial_service.model.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    Account createAccount();
    Optional<Account> getAccountById(String accountId);
    void deleteAccount(UUID accountId);
    void updateAccount(Account account);
    void transfer(Account payerAccount, Account payeeAccount, double amount);
    void deposit(Account account, double amount);
}
