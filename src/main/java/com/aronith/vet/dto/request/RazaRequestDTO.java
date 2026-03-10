package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información para el registro de una nueva raza")
public record RazaRequestDTO (

        @Schema(description = "Nombre de la raza específica", example = "Golden Retriever")
        String nombre,

        @Schema(description = "ID de la especie a la que pertenece la raza", example = "1")
        Long especieId
){ }