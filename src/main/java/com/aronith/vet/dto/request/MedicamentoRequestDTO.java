package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos para el registro o actualización de un medicamento en el inventario")
public record MedicamentoRequestDTO(

        @Schema(description = "Nombre comercial o genérico del medicamento", example = "Amoxicilina 500mg")
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @Schema(description = "Cantidad de unidades disponibles en almacén", example = "50")
        @NotNull(message = "El stock es obligatorio")
        Integer stock,

        @Schema(description = "Precio unitario de venta al público", example = "15.75")
        @NotNull(message = "El precio de venta es obligatorio")
        Double precioVenta
) {}