package com.aronith.vet.service;

import com.aronith.vet.dto.request.RazaRequestDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.model.Raza;

import java.util.List;

public interface RazaService {

    List<RazaResponseDTO> findAll();
    RazaResponseDTO save(RazaRequestDTO dto);
    List<RazaResponseDTO> findByEspecieId(Long especieId);
    Raza obtenerPorId(Long id);

}
