package com.aronith.vet.dto.request;

public record DetalleVisitaRequestDTO(
        Integer cantidad,
        Double precioAplicado,
        Long idServicio,
        Long idMedicamento
) {
}
