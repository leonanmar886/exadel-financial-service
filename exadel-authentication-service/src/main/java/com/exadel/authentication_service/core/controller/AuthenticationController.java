package com.exadel.authentication_service.core.controller;

import com.exadel.authentication_service.core.model.dto.request.LoginRequestDTO;
import com.exadel.authentication_service.core.model.dto.request.RegisterUserRequestDTO;
import com.exadel.authentication_service.core.model.dto.response.LoginResponseDTO;
import com.exadel.authentication_service.core.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        LoginResponseDTO responseDTO = authenticationService.login(request);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterUserRequestDTO request) {
        authenticationService.register(request);
        return ResponseEntity.ok("User registered");
    }
}
