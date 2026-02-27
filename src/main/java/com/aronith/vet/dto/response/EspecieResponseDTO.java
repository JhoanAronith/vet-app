package com.aronith.vet.dto.response;

import java.util.List;

public record EspecieResponseDTO(
        Long id,
        String nombre,
        List<String> nombresRazas
) {
}
