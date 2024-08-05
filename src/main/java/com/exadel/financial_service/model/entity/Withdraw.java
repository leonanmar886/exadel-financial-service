package com.exadel.financial_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Withdraw extends FinancialOperation {
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Withdraw(Double amount) {
        super(amount);
    }

    public Withdraw() {
    }
}
