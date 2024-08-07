package com.exadel.financial_service.model.entity;

import com.exadel.financial_service.model.pojo.DataAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User extends DataAudit {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    @Transient
    private String accountId;

    public User() {
        this.id = UUID.randomUUID();
    }
}
