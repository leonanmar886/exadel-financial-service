package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DataAudit;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.JOINED)
public class FinancialOperation extends DataAudit {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    private Double amount;

    public FinancialOperation(Double amount) {
        this.amount = amount;
    }

    public FinancialOperation() {
    }
}
