package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta detallada de un ítem (servicio o medicamento) registrado en la visita")
public record DetalleVisitaResponseDTO(

        @Schema(description = "Identificador único de la línea de detalle", example = "501")
        Long id,

        @Schema(description = "Unidades consumidas del ítem", example = "2")
        Integer cantidad,

        @Schema(description = "Precio unitario pactado para esta visita", example = "15.50")
        Double precioAplicado,

        @Schema(description = "Nombre del servicio o medicamento", example = "Vacuna Triple Felina")
        String nombreItem,

        @Schema(description = "Cálculo total de la línea (cantidad * precio)", example = "31.00")
        Double subtotal
) { }
