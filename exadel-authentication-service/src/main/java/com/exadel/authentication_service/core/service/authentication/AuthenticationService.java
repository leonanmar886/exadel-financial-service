package com.exadel.authentication_service.core.service.authentication;

import com.exadel.gateway.core.model.dto.request.LoginRequestDTO;

public interface AuthenticationService {
    void validateToken(String token);
    void login(LoginRequestDTO request);
}
