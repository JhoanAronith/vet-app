package com.aronith.vet.service;

import com.aronith.vet.dto.request.VisitaRequestDTO;
import com.aronith.vet.dto.response.VisitaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitaService {

    VisitaResponseDTO crearVisita(VisitaRequestDTO dto);
    List<VisitaResponseDTO> listarPorMascota(Long idMascota);
    VisitaResponseDTO obtenerPorId(Long id);
    List<VisitaResponseDTO> listarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin);

}
