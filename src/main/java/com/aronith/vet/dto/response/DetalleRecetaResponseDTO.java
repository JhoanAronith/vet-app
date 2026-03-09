package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalle de un medicamento específico dentro de una receta médica")
public record DetalleRecetaResponseDTO(

        @Schema(description = "ID único del registro de detalle", example = "501")
        Long id,

        @Schema(description = "Nombre comercial o genérico del medicamento prescrito", example = "Amoxicilina 500mg")
        String nombreMedicamento,

        @Schema(description = "Instrucciones detalladas de administración", example = "1 tableta cada 8 horas por 7 días")
        String dosis
) { }