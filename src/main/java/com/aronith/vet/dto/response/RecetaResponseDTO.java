package com.aronith.vet.dto.response;

import java.util.List;

public record RecetaResponseDTO (
        Long id,
        Long idVisita,
        String indicacionesGenerales,
        List<DetalleRecetaResponseDTO> detalles
) {}
