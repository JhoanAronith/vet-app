package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información para el registro de servicios veterinarios (Consultas, Cirugías, Baños, etc.)")
public record ServicioRequestDTO(

        @Schema(description = "Nombre del servicio prestado", example = "Consulta General")
        String nombre,

        @Schema(description = "Descripción detallada de lo que incluye el servicio",
                example = "Evaluación física completa, toma de temperatura y pesaje.")
        String descripcion,

        @Schema(description = "Costo base del servicio", example = "45.00")
        Double precio
) {}