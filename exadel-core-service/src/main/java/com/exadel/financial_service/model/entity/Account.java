package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DataAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
@AllArgsConstructor
public class Account extends DataAudit {
    @Id
    private UUID id;
    @Column(name = "account_id")
    private String accountId;

    private double balance;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transfer> transactions;

    public Account() {
        this.id = UUID.randomUUID();
        this.transactions = List.of();
    }
}
