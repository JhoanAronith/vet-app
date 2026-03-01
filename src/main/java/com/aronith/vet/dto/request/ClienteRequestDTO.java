package com.aronith.vet.dto.request;

public record ClienteRequestDTO(
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String email,
        String direccion
) {}
