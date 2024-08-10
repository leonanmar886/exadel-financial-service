package com.exadel.financial_service.service.financial_operation;

import com.exadel.financial_service.model.dto.request.CreationTransferRequestDTO;
import com.exadel.financial_service.model.entity.Account;
import com.exadel.financial_service.model.entity.Transfer;
import com.exadel.financial_service.repository.TransferRepository;
import com.exadel.financial_service.service.account.AccountService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialOperationsServiceImpl implements FinancialOperationsService {
    private final TransferRepository transferRepository;
    private final AccountService accountService;

    @Override
    @Transactional
    public void transfer(CreationTransferRequestDTO request) {
        Account payerAccount = accountService.getAccountById(request.payerAccountNumber())
                .orElseThrow(
                    () -> new EntityNotFoundException("Payer account not found")
                );

        Account payeeAccount = accountService.getAccountById(request.payeeAccountNumber())
                .orElseThrow(
                    () -> new EntityNotFoundException("Payee account not found")
                );

        double amount = request.amount();

        if (payerAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        payerAccount.setBalance(payerAccount.getBalance() - amount);
        payeeAccount.setBalance(payeeAccount.getBalance() + amount);

        accountService.updateAccount(payerAccount);
        accountService.updateAccount(payeeAccount);

        transferRepository.save(
            new Transfer(
                payerAccount,
                payeeAccount,
                amount
            )
        );
    }
}
