package com.aronith.vet.dto.response;

import java.time.LocalDateTime;

public record CitaResponseDTO(
        Long id,
        LocalDateTime fechaHora,
        String motivo,
        String estado,
        String nombreMascota
) { }
