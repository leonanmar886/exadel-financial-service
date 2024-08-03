package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private UUID id;
    private String accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    private double balance;

    @OneToMany(mappedBy = "source", fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
