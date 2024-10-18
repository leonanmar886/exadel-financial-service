package com.exadel.authentication_service.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.authentication.jwt.regular.secret}")
    private String SECRET_KEY;

    @Value("${app.authentication.jwt.service.secret}")
    private String SERVICE_TOKEN_SECRET;

    @Value("${app.authentication.jwt.regular.expiration}")
    private long expiration;

    public String generateToken(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String generateServiceToken() {
        SecretKey key = Keys.hmacShaKeyFor(SERVICE_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject("authentication-service")
                .claim("role", "SERVICE")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }
}
