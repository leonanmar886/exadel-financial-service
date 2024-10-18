package com.exadel.authentication_service.core.model.dto.request;

public record RegisterUserCoreServiceRequestDTO(
    String name,
    String email,
    String cpf
) {
}
