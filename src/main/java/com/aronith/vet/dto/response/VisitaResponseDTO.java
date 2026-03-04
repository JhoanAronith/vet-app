package com.aronith.vet.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record VisitaResponseDTO(
        Long id,
        LocalDateTime fechaHora,
        String diagnostico,
        Double temperatura,
        Double pesoEnVisita,
        String nombreMascota,
        List<DetalleVisitaResponseDTO> detalles,
        Double totalVisita
) {}
