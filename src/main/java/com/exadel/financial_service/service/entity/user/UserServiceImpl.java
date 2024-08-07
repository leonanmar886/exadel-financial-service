package com.exadel.financial_service.service.entity.user;

import com.exadel.financial_service.model.entity.User;
import com.exadel.financial_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(String name, String email, String cpf, String accountId) {
        User user = new User(
            UUID.randomUUID(),
            name,
            email,
            cpf,
            accountId
        );

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
