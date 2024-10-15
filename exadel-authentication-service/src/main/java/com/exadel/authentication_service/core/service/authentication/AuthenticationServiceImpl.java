package com.exadel.authentication_service.core.service.authentication;

import com.exadel.authentication_service.core.model.dto.request.LoginRequestDTO;
import com.exadel.authentication_service.core.model.dto.request.RegisterUserCoreServiceRequestDTO;
import com.exadel.authentication_service.core.model.dto.request.RegisterUserRequestDTO;
import com.exadel.authentication_service.core.model.dto.response.LoginResponseDTO;
import com.exadel.authentication_service.core.model.entity.User;
import com.exadel.authentication_service.core.service.userDetails.CustomUserDetailsService;
import com.exadel.authentication_service.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Value("${app.core-service.url}")
    private String coreServiceUrl;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
            request.email(),
            request.password()
        );

        Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);

        String token = jwtUtil.generateToken(authenticationResult);

        return new LoginResponseDTO(token);
    }

    @Override
    public void register(RegisterUserRequestDTO request) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User userToRegister = new User(
                request.name(),
                passwordEncoder.encode(request.password()),
                request.cpf(),
                true,
                request.email()
        );

        String token = jwtUtil.generateServiceToken();

        boolean registrationSuccess = registerUserInCoreService(userToRegister, request.email(), token);

        if (!registrationSuccess) {
            throw new RuntimeException("Register user error.");
        }

        userDetailsService.registerUser(userToRegister);

        log.info("User registered successfully.");
    }



    private boolean registerUserInCoreService(User user, String email, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RegisterUserCoreServiceRequestDTO requestDTO = new RegisterUserCoreServiceRequestDTO(
                user.getUsername(),
                email,
                user.getCpf()
        );

        HttpEntity<RegisterUserCoreServiceRequestDTO> request = new HttpEntity<>(requestDTO, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    coreServiceUrl + "/account/create",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return true;
            } else {
                log.error("Register user error: " + response.getStatusCode());
                return false;
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error communicating with the core-service: " + e.getStatusCode());
            return false;
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            return false;
        }
    }

}
