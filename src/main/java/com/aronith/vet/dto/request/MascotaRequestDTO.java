package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Información necesaria para registrar o actualizar una mascota")
public record MascotaRequestDTO(

        @Schema(description = "Nombre de la mascota", example = "Jhimmy")
        String nombre,

        @Schema(description = "Fecha de nacimiento (Formato: yyyy-MM-dd)", example = "2022-04-12")
        LocalDate fechaNacimiento,

        @Schema(description = "Género de la mascota", example = "MACHO", allowableValues = {"MACHO", "HEMBRA"})
        String genero,

        @Schema(description = "Peso actual en kilogramos", example = "12.5")
        double pesoActual,

        @Schema(description = "ID de la raza asociada", example = "3")
        Long razaId,

        @Schema(description = "ID del cliente (dueño) de la mascota", example = "1")
        Long clienteId
) {}