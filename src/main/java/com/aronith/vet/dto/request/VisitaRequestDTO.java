package com.aronith.vet.dto.request;

import java.util.List;

public record VisitaRequestDTO(
        Double temperatura,
        Double pesoEnVisita,
        String diagnostico,
        Long idMascota,
        List<DetalleVisitaRequestDTO> detalles
) { }
