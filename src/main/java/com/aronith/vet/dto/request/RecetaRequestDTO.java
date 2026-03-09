package com.aronith.vet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Datos para la creación de una receta médica completa")
public record RecetaRequestDTO(

        @Schema(description = "ID de la visita médica a la que se asocia esta receta", example = "45")
        Long idVisita,

        @Schema(description = "Instrucciones globales para el cuidado del paciente",
                example = "Mantener reposo absoluto y evitar contacto con otros animales por 48 horas.")
        String indicacionesGenerales,

        @Schema(description = "Lista detallada de medicamentos y dosis específicas")
        List<DetalleRecetaRequestDTO> detalles
) {}
