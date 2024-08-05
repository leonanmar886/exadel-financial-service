package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deposit")
public class Deposit extends FinancialOperation {

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private FinancialOperation financialOperation;

    public Deposit(Double amount) {
        super(amount);
    }

    public Deposit() {
    }

    public Deposit(Account account, Double amount) {
        super(amount);
        this.account = account;
    }

}
