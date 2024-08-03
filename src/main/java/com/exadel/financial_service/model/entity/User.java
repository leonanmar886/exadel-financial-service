package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    private Account account;

    public User() {
        this.id = UUID.randomUUID();
        this.account = null;
        this.name = null;
        this.email = null;
        this.cpf = null;
    }
}
