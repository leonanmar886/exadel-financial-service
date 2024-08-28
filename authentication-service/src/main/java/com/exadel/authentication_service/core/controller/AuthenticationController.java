package com.exadel.authentication_service.core.controller;

import com.exadel.authentication_service.core.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        authenticationService.validateToken(token);
        return ResponseEntity.ok().build();
    }
}
