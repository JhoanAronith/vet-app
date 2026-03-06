package com.aronith.vet.dto.response;

public record DetalleRecetaResponseDTO(
        Long id,
        String nombreMedicamento,
        String dosis
) {}
