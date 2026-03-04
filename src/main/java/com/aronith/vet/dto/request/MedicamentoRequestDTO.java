package com.aronith.vet.dto.request;

public record MedicamentoRequestDTO(
        String nombre,
        Integer stock,
        Double precioVenta
) {}
