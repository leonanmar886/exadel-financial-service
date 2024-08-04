package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DateAudit;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transfer extends DateAudit {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destination;

    private Double amount;

    public Transfer(Account payerAccount, Account payeeAccount, Double amount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.source = payerAccount;
        this.destination = payeeAccount;
    }

    public Transfer() {
    }
}
