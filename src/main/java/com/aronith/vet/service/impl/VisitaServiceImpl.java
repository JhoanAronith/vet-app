package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.DetalleVisitaRequestDTO;
import com.aronith.vet.dto.request.VisitaRequestDTO;
import com.aronith.vet.dto.response.DetalleVisitaResponseDTO;
import com.aronith.vet.dto.response.VisitaResponseDTO;
import com.aronith.vet.model.*;
import com.aronith.vet.repository.MascotaRepository;
import com.aronith.vet.repository.MedicamentoRepository;
import com.aronith.vet.repository.ServicioRepository;
import com.aronith.vet.repository.VisitaRepository;
import com.aronith.vet.service.VisitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitaServiceImpl implements VisitaService {

    private final VisitaRepository visitaRepository;
    private final MascotaRepository mascotaRepository;
    private final ServicioRepository servicioRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Override
    @Transactional
    public VisitaResponseDTO crearVisita(VisitaRequestDTO request) {

        Mascota mascota = mascotaRepository.findById(request.idMascota())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Visita visita = new Visita();
        visita.setTemperatura(request.temperatura());
        visita.setPesoEnVisita(request.pesoEnVisita());
        visita.setDiagnostico(request.diagnostico());
        visita.setMascota(mascota);

        if (request.detalles() != null && !request.detalles().isEmpty()) {
            List<DetalleVisita> detallesEntidad = request.detalles()
                    .stream()
                    .map(dto -> mapearDetalle(dto, visita))
                    .toList();
            visita.setDetalles(detallesEntidad);
        }
        Visita visitaGuardada = visitaRepository.save(visita);

        return mapearAResponseDTO(visitaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitaResponseDTO> listarPorMascota(Long idMascota) {
        if (!mascotaRepository.existsById(idMascota)) {
            throw new RuntimeException("Mascota no encontrada");
        }

        return visitaRepository.findByMascotaIdOrderByFechaHoraDesc(idMascota)
                .stream()
                .map(this::mapearAResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VisitaResponseDTO obtenerPorId(Long id) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con ID: " + id));

        return mapearAResponseDTO(visita);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitaResponseDTO> listarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {
        return visitaRepository.findByFechaHoraBetween(inicio, fin)
                .stream()
                .map(this::mapearAResponseDTO)
                .toList();
    }

    private DetalleVisita mapearDetalle(DetalleVisitaRequestDTO dto, Visita visita) {
        DetalleVisita detalle = new DetalleVisita();
        detalle.setCantidad(dto.cantidad());
        detalle.setVisita(visita);

        if (dto.idServicio() != null) {
            Servicio s = servicioRepository.findById(dto.idServicio()).orElseThrow();
            detalle.setServicio(s);
            detalle.setPrecioAplicado(dto.precioAplicado() != null ? dto.precioAplicado() : s.getPrecio());
        }
        else if (dto.idMedicamento() != null) {
            Medicamento m = medicamentoRepository.findById(dto.idMedicamento()).orElseThrow();
            detalle.setMedicamento(m);
            detalle.setPrecioAplicado(dto.precioAplicado() != null ? dto.precioAplicado() : m.getPrecioVenta());
        }

        return detalle;
    }

    private VisitaResponseDTO mapearAResponseDTO(Visita v) {
        List<DetalleVisitaResponseDTO> detallesDTO = v.getDetalles().stream()
                .map(d -> new DetalleVisitaResponseDTO(
                        d.getId(),
                        d.getCantidad(),
                        d.getPrecioAplicado(),
                        d.getServicio() != null ? d.getServicio().getNombre() : d.getMedicamento().getNombre(),
                        d.getCantidad() * d.getPrecioAplicado()
                ))
                .toList();

        Double total = detallesDTO.stream().mapToDouble(DetalleVisitaResponseDTO::subtotal).sum();

        return new VisitaResponseDTO(
                v.getId(),
                v.getFechaHora(),
                v.getDiagnostico(),
                v.getTemperatura(),
                v.getPesoEnVisita(),
                v.getMascota().getNombre(),
                detallesDTO,
                total
        );
    }

}
