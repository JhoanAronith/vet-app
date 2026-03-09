package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para el registro o actualización de un medicamento en el inventario")
public record MedicamentoRequestDTO(

        @Schema(description = "Nombre comercial o genérico del medicamento", example = "Amoxicilina 500mg")
        String nombre,

        @Schema(description = "Cantidad de unidades disponibles en almacén", example = "50")
        Integer stock,

        @Schema(description = "Precio unitario de venta al público", example = "15.75")
        Double precioVenta
) {}