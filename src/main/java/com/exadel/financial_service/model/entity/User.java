package com.exadel.financial_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "users")
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String cpf;
}
