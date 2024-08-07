package com.exadel.financial_service.service.core;

public interface CoreService {
    void createAccount(String name, String email, String cpf);
    void transfer(String payerAccountNumber, String payeeAccountNumber, double amount);
    void deposit(String accountNumber, double amount);
}
