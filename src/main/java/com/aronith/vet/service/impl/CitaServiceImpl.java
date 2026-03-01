package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.CitaRequestDTO;
import com.aronith.vet.dto.response.CitaResponseDTO;
import com.aronith.vet.model.Cita;
import com.aronith.vet.model.Mascota;
import com.aronith.vet.repository.CitaRepository;
import com.aronith.vet.service.CitaService;
import com.aronith.vet.service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final MascotaService mascotaService;

    @Override
    public CitaResponseDTO guardar(CitaRequestDTO dto) {
        Mascota mascota = mascotaService.buscarMascotaPorId(dto.idMascota());
        if (citaRepository.existsByFechaHora(dto.fechaHora())) {
            throw new RuntimeException("Ya existe una cita en: " + dto.fechaHora());
        }
        Cita cita = new Cita();
        cita.setFechaHora(dto.fechaHora());
        cita.setMotivo(dto.motivo());
        cita.setEstado("PENDIENTE");
        cita.setMascota(mascota);
        Cita citaGuardada = citaRepository.save(cita);
        return mapearADto(citaGuardada);
    }

    @Override
    public List<CitaResponseDTO> obtenerTodos() {
        return citaRepository.findAll()
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public List<CitaResponseDTO> obtenerPorFecha(LocalDate fecha) {
        LocalDateTime inicioDia = fecha.atStartOfDay();
        LocalDateTime finDia = fecha.atTime(LocalTime.MAX);

        return citaRepository.findByFechaHoraBetween(inicioDia, finDia)
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public List<CitaResponseDTO> obtenerHistorialPorMascota(Long idMascota) {
        return citaRepository.findByMascotaId(idMascota)
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public List<CitaResponseDTO> obtenerPorEstado(String estado) {
        return citaRepository.findByEstado(estado)
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Transactional
    @Override
    public CitaResponseDTO cancelarCita(Long idCita) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(()-> new RuntimeException("Cita no encontrada"));
        if ("COMPLETADA".equals(cita.getEstado())) {
            throw new RuntimeException("No se puede cancelar una cita que ya ha sido atendida");
        }
        if ("CANCELADA".equals(cita.getEstado())) {
            throw new RuntimeException("La cita ya se encuentra cancelada");
        }
        cita.setEstado("CANCELADA");
        Cita citaActualizada = citaRepository.save(cita);
        return mapearADto(citaActualizada);
    }

    private CitaResponseDTO mapearADto(Cita cita) {
        return new CitaResponseDTO(
                cita.getFechaHora(),
                cita.getMotivo(),
                cita.getEstado(),
                cita.getMascota().getNombre()
        );
    }

}
