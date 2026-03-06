package com.aronith.vet.dto.request;

import java.util.List;

public record RecetaRequestDTO(
        Long idVisita,
        String indicacionesGenerales,
        List<DetalleRecetaRequestDTO> detalles
) {}
