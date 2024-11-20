package com.jvictornascimento.auth_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRegiterDto(
        @NotNull
        String name,
        @NotNull
        String password,
        @NotNull
        @Email
        String email


) {
}
