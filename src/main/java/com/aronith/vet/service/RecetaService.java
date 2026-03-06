package com.aronith.vet.service;

import com.aronith.vet.dto.request.RecetaRequestDTO;
import com.aronith.vet.dto.response.RecetaResponseDTO;

public interface RecetaService {

    RecetaResponseDTO crear(RecetaRequestDTO request);
    RecetaResponseDTO obtenerPorVisita(Long idVisita);
    RecetaResponseDTO obtenerPorId(Long id);

}
