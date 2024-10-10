package com.exadel.authentication_service.core.service.authentication;

import com.exadel.authentication_service.core.model.dto.request.LoginRequestDTO;
import com.exadel.authentication_service.core.model.dto.request.RegisterUserRequestDTO;
import com.exadel.authentication_service.core.model.dto.response.LoginResponseDTO;

public interface AuthenticationService {
    LoginResponseDTO login(LoginRequestDTO request);
    void register(RegisterUserRequestDTO request);
}
