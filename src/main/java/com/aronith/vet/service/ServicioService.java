package com.aronith.vet.service;

import com.aronith.vet.dto.request.ServicioRequestDTO;
import com.aronith.vet.dto.response.ServicioResponseDTO;

import java.util.List;

public interface ServicioService {

    ServicioResponseDTO guardar(ServicioRequestDTO dto);
    List<ServicioResponseDTO> listarTodos();

}
