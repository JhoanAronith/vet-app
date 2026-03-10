package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta detallada de una raza con su clasificación taxonómica")
public record RazaResponseDTO(

        @Schema(description = "Identificador único de la raza", example = "3")
        Long id,

        @Schema(description = "Nombre específico de la raza", example = "Golden Retriever")
        String nombre,

        @Schema(description = "Nombre de la especie a la que pertenece", example = "Canina")
        String nombreEspecie
) { }
