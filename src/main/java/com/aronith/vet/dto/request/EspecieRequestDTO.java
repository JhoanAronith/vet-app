package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para el registro de una nueva especie animal")
public record EspecieRequestDTO (
        @Schema(description = "Nombre común de la especie", example = "Canina")
        String nombre
) { }
