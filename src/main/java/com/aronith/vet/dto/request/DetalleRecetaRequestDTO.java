package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información individual de cada medicamento asignado en una receta")
public record DetalleRecetaRequestDTO (

        @Schema(description = "ID del medicamento registrado en el catálogo", example = "10")
        Integer idMedicamento,

        @Schema(description = "Instrucciones de dosificación para el tratamiento", example = "1 tableta cada 8 horas por 5 días")
        String dosis
) {}
