package com.aronith.vet.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Datos para el registro de una visita médica veterinaria")
public record VisitaRequestDTO(

        @Schema(description = "Temperatura corporal de la mascota (°C)", example = "38.5")
        Double temperatura,

        @Schema(description = "Peso registrado específicamente en esta visita (kg)", example = "12.8")
        Double pesoEnVisita,

        @Schema(description = "Diagnóstico o hallazgos clínicos de la consulta",
                example = "Infección leve en el oído derecho, requiere limpieza y gotas.")
        String diagnostico,

        @Schema(description = "ID de la mascota que asiste a la consulta", example = "1")
        Long idMascota,

        @Schema(description = "Lista de servicios y medicamentos aplicados en la visita")
        List<DetalleVisitaRequestDTO> detalles
) { }