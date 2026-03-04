package com.aronith.vet.dto.response;

public record DetalleVisitaResponseDTO(
        Long id,
        Integer cantidad,
        Double precioAplicado,
        String nombreItem,
        Double subtotal
) {}
