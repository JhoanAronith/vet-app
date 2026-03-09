package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Respuesta detallada con la información consolidada de la mascota")
public record MascotaResponseDTO (

        @Schema(description = "Identificador único de la mascota", example = "10")
        Long id,

        @Schema(description = "Nombre de la mascota", example = "Jhimmy")
        String nombre,

        @Schema(description = "Fecha de nacimiento registrada", example = "2022-04-12")
        LocalDate fechaNacimiento,

        @Schema(description = "Género de la mascota (MACHO/HEMBRA)", example = "MACHO")
        String genero,

        @Schema(description = "Último peso registrado en kilogramos", example = "12.5")
        double pesoActual,

        @Schema(description = "Nombre descriptivo de la raza", example = "Schnauzer")
        String nombreRaza,

        @Schema(description = "Nombre completo del dueño/cliente", example = "Jhoan Aronith")
        String nombreCliente
) { }
