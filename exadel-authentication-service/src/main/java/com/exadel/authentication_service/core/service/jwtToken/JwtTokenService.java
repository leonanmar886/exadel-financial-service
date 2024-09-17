package com.exadel.authentication_service.core.service.jwtToken;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
}
