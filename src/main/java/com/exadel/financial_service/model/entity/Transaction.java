package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DateAudit;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transaction extends DateAudit {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destination;

    private double amount;

    @Column(name = "transaction_date")
    private Date date;
}
