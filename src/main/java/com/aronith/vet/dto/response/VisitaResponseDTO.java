package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Respuesta integral que consolida toda la información de una visita médica")
public record VisitaResponseDTO(

        @Schema(description = "ID único de la visita realizada", example = "1001")
        Long id,

        @Schema(description = "Fecha y hora exacta del registro clínico", example = "2026-03-08T15:30:00")
        LocalDateTime fechaHora,

        @Schema(description = "Resumen del diagnóstico o hallazgos del veterinario",
                example = "Paciente con cuadro de deshidratación leve y sensibilidad abdominal.")
        String diagnostico,

        @Schema(description = "Temperatura corporal registrada (°C)", example = "38.2")
        Double temperatura,

        @Schema(description = "Peso de la mascota al momento de la consulta (kg)", example = "8.45")
        Double pesoEnVisita,

        @Schema(description = "Nombre de la mascota atendida", example = "Jhimmy")
        String nombreMascota,

        @Schema(description = "Desglose de servicios y productos aplicados durante la sesión")
        List<DetalleVisitaResponseDTO> detalles,

        @Schema(description = "Suma total de todos los subtotales de la visita", example = "125.50")
        Double totalVisita
) {}
