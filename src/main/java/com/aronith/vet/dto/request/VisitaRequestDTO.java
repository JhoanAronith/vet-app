package com.aronith.vet.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Datos para el registro de una visita médica veterinaria")
public record VisitaRequestDTO(

        @Schema(description = "Temperatura corporal de la mascota (°C)", example = "38.5")
        @NotNull(message = "La temperatura no puede ser null")
        Double temperatura,

        @Schema(description = "Peso registrado específicamente en esta visita (kg)", example = "12.8")
        @NotNull(message = "El peso no puede ser null")
        Double pesoEnVisita,

        @Schema(description = "Diagnóstico o hallazgos clínicos de la consulta",
                example = "Infección leve en el oído derecho, requiere limpieza y gotas.")
        @NotBlank(message = "El diagnostico es obligatorio")
        String diagnostico,

        @Schema(description = "ID de la mascota que asiste a la consulta", example = "1")
        @NotNull(message = "El ID de la mascota no puede ser null")
        Long idMascota,

        @Schema(description = "Lista de servicios y medicamentos aplicados en la visita")
        @NotNull(message = "La lista de servicios y medicamento es obligatoria")
        List<DetalleVisitaRequestDTO> detalles
) { }