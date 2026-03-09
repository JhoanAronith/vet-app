package com.aronith.vet.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Datos de respuesta tras consultar o registrar una cita")
public record CitaResponseDTO(

        @Schema(description = "ID único de la cita en la base de datos", example = "25")
        Long id,

        @Schema(description = "Fecha y hora programada (Formato ISO)", example = "2026-03-15T10:30:00")
        LocalDateTime fechaHora,

        @Schema(description = "Motivo registrado para la consulta", example = "Control de vacunas")
        String motivo,

        @Schema(description = "Estado actual de la cita", example = "PROGRAMADA",
                allowableValues = {"PROGRAMADA", "CONFIRMADA", "CANCELADA", "COMPLETADA"})
        String estado,

        @Schema(description = "Nombre de la mascota asociada a la cita", example = "Jhimmy")
        String nombreMascota
) { }
