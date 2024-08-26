package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account payer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account payee;

    private Double amount;

    public Transfer(Account payer, Account payee, Double amount) {
        this.amount = amount;
        this.payer = payer;
        this.payee = payee;
    }
}
