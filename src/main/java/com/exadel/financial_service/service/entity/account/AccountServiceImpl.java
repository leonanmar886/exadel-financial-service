package com.exadel.financial_service.service.entity.account;

import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount() {
        Account account = new Account(
                UUID.randomUUID(),
                UUID.randomUUID().toString(),
                0.0,
                List.of()
        );

        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void transfer(Account payerAccount, Account payeeAccount, double amount) {
        payerAccount.setBalance(payerAccount.getBalance() - amount);
        payeeAccount.setBalance(payeeAccount.getBalance() + amount);

        updateAccount(payerAccount);
        updateAccount(payeeAccount);
    }

    @Override
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        updateAccount(account);
    }
}
