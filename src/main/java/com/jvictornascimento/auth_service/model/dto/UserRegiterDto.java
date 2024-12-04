package com.jvictornascimento.auth_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRegiterDto(
        @NotNull
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        String password

) {
}
