package com.exadel.financial_service.service.core;

import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.service.entity.account.AccountService;
import com.exadel.financial_service.service.entity.financial_operation.FinancialOperationsService;
import com.exadel.financial_service.service.entity.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreServiceImpl implements CoreService {
    private final AccountService accountService;
    private final UserService userService;
    private final FinancialOperationsService financialOperationsService;

    @Override
    public void createAccount(String name, String email, String cpf) {
        if (userService.findByCpf(cpf).isPresent()) {
            throw new IllegalArgumentException("User with this cpf already exists");
        }

        if (userService.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        Account account = accountService.createAccount();
        userService.createUser(name, email, cpf, account.getAccountId());
    }

    @Override
    public void transfer(String payerAccountNumber, String payeeAccountNumber, double amount) {
        Account payerAccount = accountService.getAccountById(payerAccountNumber)
            .orElseThrow(() -> new EntityNotFoundException("Payer account not found"));

        Account payeeAccount = accountService.getAccountById(payeeAccountNumber)
            .orElseThrow(() -> new EntityNotFoundException("Payee account not found"));

        if (payerAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        accountService.transfer(payerAccount, payeeAccount, amount);

        financialOperationsService.transfer(payerAccount, payeeAccount, amount);
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = accountService.getAccountById(accountNumber)
            .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        accountService.deposit(account, amount);

        financialOperationsService.deposit(account, amount);
    }
}
