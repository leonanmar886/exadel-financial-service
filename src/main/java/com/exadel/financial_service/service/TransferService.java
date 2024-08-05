package com.exadel.financial_service.service;

import com.exadel.financial_service.model.dto.request.CreationTransferRequestDTO;
import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.model.entity.FinancialOperation;
import com.exadel.financial_service.model.entity.Transfer;
import com.exadel.financial_service.repository.FinancialOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final FinancialOperationRepository financialOperationRepository;
    private final AccountService accountService;

    public void transfer(CreationTransferRequestDTO request) {
        Account payerAccount = accountService.getAccountByNumber(request.payerAccountNumber());
        Account payeeAccount = accountService.getAccountByNumber(request.payeeAccountNumber());

        if(payerAccount.getBalance() < request.amount()) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        payerAccount.setBalance(payerAccount.getBalance() - request.amount());
        payeeAccount.setBalance(payeeAccount.getBalance() + request.amount());

        accountService.updateAccount(payerAccount);
        accountService.updateAccount(payeeAccount);

        financialOperationRepository.save(
            new Transfer(
                payerAccount,
                payeeAccount,
                request.amount()
            )
        );
    }
}
