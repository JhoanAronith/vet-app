package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalle individual de los servicios o medicamentos aplicados durante una visita")
public record DetalleVisitaRequestDTO(

        @Schema(description = "Cantidad del ítem aplicado (servicio o medicamento)", example = "1")
        Integer cantidad,

        @Schema(description = "Precio final cobrado (puede diferir del precio base)", example = "25.50")
        Double precioAplicado,

        @Schema(description = "ID del servicio realizado (opcional si es medicamento)", example = "4")
        Long idServicio,

        @Schema(description = "ID del medicamento suministrado (opcional si es servicio)", example = "12")
        Long idMedicamento
) { }
