package com.exadel.financial_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    private Account account;
}
