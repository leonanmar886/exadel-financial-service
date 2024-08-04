package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DateAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
@AllArgsConstructor
public class Account extends DateAudit {
    @Id
    private UUID id;
    @Column(name = "account_number")
    private String accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    private double balance;

    @OneToMany(mappedBy = "source", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Account() {
        this.id = UUID.randomUUID();
        this.accountNumber = "0";
        this.owner = null;
        this.balance = 0.0;
        this.transactions = List.of();
    }
}
