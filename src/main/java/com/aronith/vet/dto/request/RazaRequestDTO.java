package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Información para el registro de una nueva raza")
public record RazaRequestDTO (

        @Schema(description = "Nombre de la raza específica", example = "Golden Retriever")
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @Schema(description = "ID de la especie a la que pertenece la raza", example = "1")
        @NotNull(message = "El ID de la especie no puede ser null")
        Long especieId
){ }