package com.aronith.vet.service;

import com.aronith.vet.dto.request.CitaRequestDTO;
import com.aronith.vet.dto.response.CitaResponseDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    CitaResponseDTO guardar(CitaRequestDTO dto);
    List<CitaResponseDTO> obtenerTodos();
    List<CitaResponseDTO> obtenerPorFecha(LocalDate fecha);
    List<CitaResponseDTO> obtenerHistorialPorMascota(Long idMascota);
    List<CitaResponseDTO> obtenerPorEstado(String estado);
    CitaResponseDTO cancelarCita(Long idCita);

}
