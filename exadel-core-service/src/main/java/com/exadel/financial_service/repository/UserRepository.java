package com.exadel.financial_service.repository;

import com.exadel.financial_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
}
