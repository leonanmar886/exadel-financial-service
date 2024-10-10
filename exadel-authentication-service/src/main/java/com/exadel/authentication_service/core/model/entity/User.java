package com.exadel.authentication_service.core.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String username;

    private String password;

    private String role;

    private String cpf;

    private boolean enabled;

    public User(String username, String password, String cpf, boolean enabled) {
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.enabled = enabled;
    }
}
