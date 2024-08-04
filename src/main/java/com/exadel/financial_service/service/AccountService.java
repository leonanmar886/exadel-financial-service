package com.exadel.financial_service.service;

import com.exadel.financial_service.model.dto.request.CreateAccountRequestDTO;
import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.model.entity.User;
import com.exadel.financial_service.repository.AccountRepository;
import com.exadel.financial_service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account createAccount(CreateAccountRequestDTO request) {
        if(userRepository.findByCpf(request.cpf()).isPresent()) {
            throw new IllegalArgumentException("User with this CPF already has an account");
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("User with this email already has an account");
        }

        User user = new User(
                UUID.randomUUID(),
                request.name(),
                request.email(),
                request.cpf(),
                null
        );

        Account account = new Account(
                UUID.randomUUID(),
                UUID.randomUUID().toString(),
                user,
                0.0,
                List.of()
        );

        userRepository.save(user);

        return accountRepository.save(account);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }
}
