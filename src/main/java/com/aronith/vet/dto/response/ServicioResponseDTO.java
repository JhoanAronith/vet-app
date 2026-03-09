package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta detallada con la información del servicio veterinario")
public record ServicioResponseDTO(

        @Schema(description = "Identificador único del servicio en el sistema", example = "50")
        Long id,

        @Schema(description = "Nombre comercial del servicio prestado", example = "Consulta Especializada")
        String nombre,

        @Schema(description = "Descripción de lo que incluye el procedimiento",
                example = "Evaluación por especialista, diagnóstico por imagen básico y receta.")
        String descripcion,

        @Schema(description = "Precio vigente del servicio", example = "60.00")
        Double precio
) {}
