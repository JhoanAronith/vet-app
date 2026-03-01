package com.aronith.vet.dto.response;

public record ClienteResponseDTO(
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String email,
        String direccion
) { }
