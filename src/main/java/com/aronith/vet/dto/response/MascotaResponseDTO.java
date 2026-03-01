package com.aronith.vet.dto.response;

import java.time.LocalDate;

public record MascotaResponseDTO (
        String nombre,
        LocalDate fechaNacimiento,
        String genero,
        double pesoActual,
        String nombreRaza,
        String nombreCliente
){}
