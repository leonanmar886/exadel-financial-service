package com.exadel.financial_service.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

public record CreateAccountRequestDTO(
        @NotNull(message = "Name cannot be null")
        String name,
        @NotNull(message = "Email cannot be null")
        @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
        String email,
        @NotNull(message = "CPF cannot be null")
        String cpf
) {
}

//@Data
//public class CreateAccountRequestDTO {
//    @NotNull(message = "Name cannot be null")
//    private String name;
//    @NotNull(message = "Email cannot be null")
//    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
//    private String email;
//    @NotNull(message = "CPF cannot be null")
//    private String cpf;
//
//    public CreateAccountRequestDTO(String name, String email, String cpf){
//        this.name = name;
//        this.email = email;
//        this.cpf = cpf;
//    }
//
//}