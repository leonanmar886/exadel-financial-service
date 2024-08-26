package com.exadel.financial_service.service.account;

import com.exadel.financial_service.model.dto.request.CreateAccountRequestDTO;
import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.repository.AccountRepository;
import com.exadel.financial_service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public void createAccount(CreateAccountRequestDTO request) {
        Account account = new Account(
                UUID.randomUUID(),
                UUID.randomUUID().toString(),
                0.0,
                List.of()
        );

        userService.createUser(
            request.name(),
            request.email(),
            request.cpf(),
            account.getAccountId()
        );

        accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(String accountNumber) {
        return accountRepository.findByAccountId(accountNumber);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

}
