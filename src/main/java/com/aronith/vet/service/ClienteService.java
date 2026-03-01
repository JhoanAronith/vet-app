package com.aronith.vet.service;

import com.aronith.vet.dto.request.ClienteRequestDTO;
import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.dto.response.MascotaResumenDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    ClienteResponseDTO guardar(ClienteRequestDTO dto);
    Optional<ClienteResponseDTO> buscarPorId(Long id);
    Optional<ClienteResponseDTO> buscarPorDni(String dni);
    List<MascotaResumenDTO> listarMascotasPorCliente(Long idCliente);
    List<ClienteResponseDTO> listarTodos();

}
