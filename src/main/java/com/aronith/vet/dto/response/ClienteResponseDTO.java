package com.aronith.vet.dto.response;

public record ClienteResponseDTO(
        Long id,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String email,
        String direccion
) { }
