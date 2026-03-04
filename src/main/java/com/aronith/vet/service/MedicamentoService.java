package com.aronith.vet.service;

import com.aronith.vet.dto.request.MedicamentoRequestDTO;
import com.aronith.vet.dto.response.MedicamentoResponseDTO;

import java.util.List;

public interface MedicamentoService {

    MedicamentoResponseDTO guardar(MedicamentoRequestDTO dto);
    List<MedicamentoResponseDTO> listar();

}
