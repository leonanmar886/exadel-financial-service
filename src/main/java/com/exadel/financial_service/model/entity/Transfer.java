package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;

@Entity
public class Transfer extends FinancialOperation {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destination;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private FinancialOperation financialOperation;

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
