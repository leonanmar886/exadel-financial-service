package com.exadel.authentication_service.core.service.authentication;

import com.exadel.gateway.core.model.dto.request.LoginRequestDTO;
import com.exadel.gateway.core.service.jwtToken.JwtTokenService;
import com.exadel.gateway.core.service.userDetails.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void validateToken(String token) {
        String jwt = token.substring(7);
        String username = jwtTokenService.extractUsername(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!jwtTokenService.validateToken(jwt, userDetails)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    @Override
    public void login(LoginRequestDTO request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                request.username(),
                request.password()
        );

        Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);
    }
}
