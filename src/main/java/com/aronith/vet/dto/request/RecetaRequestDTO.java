package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Datos para la creación de una receta médica completa")
public record RecetaRequestDTO(

        @Schema(description = "ID de la visita médica a la que se asocia esta receta", example = "45")
        @NotNull(message = "El ID de visita es obligatorio")
        Long idVisita,

        @Schema(description = "Instrucciones globales para el cuidado del paciente",
                example = "Mantener reposo absoluto y evitar contacto con otros animales por 48 horas.")
        @NotBlank(message = "Las indicaciones generales son obligatorias")
        String indicacionesGenerales,

        @Schema(description = "Lista detallada de medicamentos y dosis específicas")
        @NotNull(message = "La lista de medicamentos no puede ser null")
        List<DetalleRecetaRequestDTO> detalles
) {}
