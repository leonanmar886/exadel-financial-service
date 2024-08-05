package com.exadel.financial_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transfer extends FinancialOperation {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destination;

    public Transfer(Double amount) {
        super(amount);
    }

    public Transfer() {
    }

    public Transfer(Account source, Account destination, Double amount) {
        super(amount);
        this.source = source;
        this.destination = destination;
    }
}
