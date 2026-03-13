package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Información individual de cada medicamento asignado en una receta")
public record DetalleRecetaRequestDTO (

        @Schema(description = "ID del medicamento registrado en el catálogo", example = "10")
        @NotNull(message = "El ID del medicamento no puede ser null")
        Integer idMedicamento,

        @Schema(description = "Instrucciones de dosificación para el tratamiento", example = "1 tableta cada 8 horas por 5 días")
        @NotBlank(message = "La dosis es obligatoria")
        String dosis
) {}
