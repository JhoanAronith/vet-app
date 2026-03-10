package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con la información detallada del medicamento e inventario")
public record MedicamentoResponseDTO(

        @Schema(description = "Identificador único del medicamento", example = "101")
        Long id,

        @Schema(description = "Nombre comercial o genérico del medicamento", example = "Amoxicilina 500mg")
        String nombre,

        @Schema(description = "Cantidad de unidades actualmente en almacén", example = "50")
        Integer stock,

        @Schema(description = "Precio unitario de venta registrado", example = "15.75")
        Double precioVenta
) { }
