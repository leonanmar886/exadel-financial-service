package com.exadel.financial_service.service.user;

import com.exadel.financial_service.model.entity.User;

import java.util.Optional;

public interface UserService {
    void createUser(String name, String email, String cpf, String accountId);
    User getUserById(String id);
    User save(User user);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
}
