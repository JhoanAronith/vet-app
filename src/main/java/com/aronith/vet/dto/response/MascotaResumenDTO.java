package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumen básico de la mascota para listados y selectores")
public record MascotaResumenDTO (

        @Schema(description = "Identificador único de la mascota", example = "10")
        Long id,

        @Schema(description = "Nombre de la mascota", example = "Jhimmy")
        String nombre,

        @Schema(description = "Nombre de la raza a la que pertenece", example = "Schnauzer")
        String razaNombre,

        @Schema(description = "Nombre de la especie general", example = "Canina")
        String especieNombre
) { }
