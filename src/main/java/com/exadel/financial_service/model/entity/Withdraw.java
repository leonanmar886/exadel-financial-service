package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;

@Entity
public class Withdraw extends FinancialOperation {
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private FinancialOperation financialOperation;

    public Withdraw(Double amount) {
        super(amount);
    }

    public Withdraw() {
    }
}
