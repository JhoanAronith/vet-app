package com.aronith.vet.dto.request;

import java.time.LocalDate;

public record MascotaRequestDTO(
        String nombre,
        LocalDate fechaNacimiento,
        String genero,
        double pesoActual,
        Long razaId,
        Long clienteId
) {}
