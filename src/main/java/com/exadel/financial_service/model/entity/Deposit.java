package com.exadel.financial_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Deposit extends FinancialOperation {
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Deposit(Double amount) {
        super(amount);
    }

    public Deposit() {
    }
}
