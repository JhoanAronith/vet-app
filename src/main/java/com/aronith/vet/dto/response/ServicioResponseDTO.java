package com.aronith.vet.dto.response;

public record ServicioResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        Double precio
) {}
