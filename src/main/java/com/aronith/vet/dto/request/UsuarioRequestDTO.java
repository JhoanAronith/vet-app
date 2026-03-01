package com.aronith.vet.dto.request;

public record UsuarioRequestDTO(
        String username,
        String password,
        String email
) {}
