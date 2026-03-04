package com.aronith.vet.dto.request;

public record ServicioRequestDTO(
        String nombre,
        String descripcion,
        Double precio
) {}
