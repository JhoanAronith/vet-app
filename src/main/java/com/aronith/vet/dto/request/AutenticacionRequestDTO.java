package com.aronith.vet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutenticacionRequestDTO(
        @NotBlank @NotNull String email,
        @NotBlank @NotNull String password
) {}