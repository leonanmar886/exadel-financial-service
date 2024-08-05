package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DateAudit;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.JOINED)
public class FinancialOperation extends DateAudit {
    @Id
    private UUID id;

    private Double amount;

    public FinancialOperation(Double amount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
    }

    public FinancialOperation() {
    }
}
