package com.aronith.vet.service;

import com.aronith.vet.dto.request.EspecieRequestDTO;
import com.aronith.vet.dto.response.EspecieResponseDTO;
import com.aronith.vet.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieService {

    EspecieResponseDTO guardar(EspecieRequestDTO dto);
    List<EspecieResponseDTO> listarTodos();
    Especie obtenerPorId(Long id);

}
