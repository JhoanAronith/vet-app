package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Detalle individual de los servicios o medicamentos aplicados durante una visita")
public record DetalleVisitaRequestDTO(

        @Schema(description = "Cantidad del ítem aplicado (servicio o medicamento)", example = "1")
        @NotNull(message = "La cantidad no puede ser null")
        Integer cantidad,

        @Schema(description = "Precio final cobrado (puede diferir del precio base)", example = "25.50")
        @NotNull(message = "La precio aplicado no puede ser null")
        Double precioAplicado,

        @Schema(description = "ID del servicio realizado (opcional si es medicamento)", example = "4")
        @NotNull(message = "El ID del servicio no puede ser null")
        Long idServicio,

        @Schema(description = "ID del medicamento suministrado (opcional si es servicio)", example = "12")
        @NotNull(message = "El ID del medicamento no puede ser null")
        Long idMedicamento
) { }
