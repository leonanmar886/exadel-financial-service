package com.exadel.financial_service.service.entity.financial_operation;

import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.model.entity.Deposit;
import com.exadel.financial_service.model.entity.Transfer;
import com.exadel.financial_service.repository.FinancialOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialOperationsServiceImpl implements FinancialOperationsService {
    private final FinancialOperationRepository financialOperationRepository;

    public void transfer(Account payerAccount, Account payeeAccount, double amount) {
        financialOperationRepository.save(
            new Transfer(
                payerAccount,
                payeeAccount,
                amount
            )
        );
    }

    public void deposit(Account account, double amount) {
        financialOperationRepository.save(
            new Deposit(
                account,
                amount
            )
        );
    }
}
