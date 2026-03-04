package com.aronith.vet.dto.response;

public record MedicamentoResponseDTO(
        Long id,
        String nombre,
        Integer stock,
        Double precioVenta
) {}
