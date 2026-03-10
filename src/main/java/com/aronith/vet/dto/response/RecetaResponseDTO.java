package com.aronith.vet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Respuesta con la información completa de una receta médica y sus prescripciones")
public record RecetaResponseDTO (

        @Schema(description = "Identificador único de la receta generada", example = "201")
        Long id,

        @Schema(description = "ID de la visita médica asociada a esta receta", example = "45")
        Long idVisita,

        @Schema(description = "Instrucciones globales de cuidado y manejo del paciente",
                example = "Mantener al paciente en un lugar cálido y administrar abundante agua.")
        String indicacionesGenerales,

        @Schema(description = "Lista de medicamentos específicos y sus dosis correspondientes")
        List<DetalleRecetaResponseDTO> detalles
) { }
