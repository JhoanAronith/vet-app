package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumen simplificado de los datos del cliente para listados")
public record ClienteResumenDto(

        @Schema(description = "Nombre completo del cliente", example = "Jhoan Aronith")
        String nombre,

        @Schema(description = "Documento Nacional de Identidad", example = "70654321")
        String dni,

        @Schema(description = "Teléfono principal de contacto", example = "987654321")
        String telefono
) { }
