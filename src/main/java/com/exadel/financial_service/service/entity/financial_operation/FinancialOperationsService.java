package com.exadel.financial_service.service.entity.financial_operation;

import com.exadel.financial_service.model.entity.Account;

public interface FinancialOperationsService {
    void transfer(Account payerAccount, Account payeeAccount, double amount);
    void deposit(Account account, double amount);
}
