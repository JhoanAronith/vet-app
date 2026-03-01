package com.aronith.vet.service;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.dto.response.ClienteResumenDto;
import com.aronith.vet.dto.response.MascotaResponseDTO;
import com.aronith.vet.model.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {

    MascotaResponseDTO guardar (MascotaRequestDTO dto);
    List<MascotaResponseDTO> listarTodos();
    List<MascotaResponseDTO> buscarPorNombre(String nombre);
    Optional<ClienteResumenDto> buscarClientePorMascotaId(Long id);
    Mascota buscarMascotaPorId(Long id);

}
