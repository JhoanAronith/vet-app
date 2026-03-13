package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Información necesaria para registrar o actualizar una mascota")
public record MascotaRequestDTO(

        @Schema(description = "Nombre de la mascota", example = "Jhimmy")
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @Schema(description = "Fecha de nacimiento (Formato: yyyy-MM-dd)", example = "2022-04-12")
        @NotNull(message = "La fecha no puede ser null")
        LocalDate fechaNacimiento,

        @Schema(description = "Género de la mascota", example = "MACHO", allowableValues = {"MACHO", "HEMBRA"})
        @NotBlank(message = "El genero es obligatorio")
        String genero,

        @Schema(description = "Peso actual en kilogramos", example = "12.5")
        @NotNull(message = "El peso no puede ser null")
        double pesoActual,

        @Schema(description = "ID de la raza asociada", example = "3")
        @NotNull(message = "El ID de la raza no puede ser null")
        Long razaId,

        @Schema(description = "ID del cliente (dueño) de la mascota", example = "1")
        @NotNull(message = "El ID del cliente no puede ser null")
        Long clienteId
) {}