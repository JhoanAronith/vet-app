package com.aronith.vet.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Respuesta detallada de una especie con sus razas asociadas")
public record EspecieResponseDTO(

        @Schema(description = "Identificador único de la especie", example = "1")
        Long id,

        @Schema(description = "Nombre de la especie (categoría general)", example = "Canina")
        String nombre,

        @Schema(description = "Lista de nombres de las razas pertenecientes a esta especie")
        List<String> nombresRazas
) { }
