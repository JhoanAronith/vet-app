package com.aronith.vet.service;

import com.aronith.vet.dto.request.RazaRequestDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;

import java.util.List;

public interface RazaService {

    List<RazaResponseDTO> findAll();
    RazaResponseDTO save(RazaRequestDTO dto);
    List<RazaResponseDTO> findByEspecieId(Long especieId);

}
