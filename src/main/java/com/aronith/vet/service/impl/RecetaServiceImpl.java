package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.RecetaRequestDTO;
import com.aronith.vet.dto.response.DetalleRecetaResponseDTO;
import com.aronith.vet.dto.response.RecetaResponseDTO;
import com.aronith.vet.model.DetalleReceta;
import com.aronith.vet.model.Medicamento;
import com.aronith.vet.model.Receta;
import com.aronith.vet.model.Visita;
import com.aronith.vet.repository.MedicamentoRepository;
import com.aronith.vet.repository.RecetaRepository;
import com.aronith.vet.repository.VisitaRepository;
import com.aronith.vet.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final VisitaRepository visitaRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Override
    @Transactional
    public RecetaResponseDTO crear(RecetaRequestDTO request) {
        Visita visita = visitaRepository.findById(request.idVisita())
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con ID: " + request.idVisita()));

        Receta receta = new Receta();
        receta.setIndicacionesGenerales(request.indicacionesGenerales());
        receta.setVisita(visita);

        List<DetalleReceta> detalles = request.detalles().stream()
                .map(det -> {
                    DetalleReceta detalle = new DetalleReceta();
                    Medicamento med = medicamentoRepository.findById(det.idMedicamento().longValue())
                            .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + det.idMedicamento()));
                    detalle.setMedicamento(med);
                    detalle.setDosis(det.dosis());
                    detalle.setReceta(receta);
                    return detalle;
                }).toList();

        receta.setDetalles(detalles);

        Receta guardada = recetaRepository.save(receta);
        return mapearAResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public RecetaResponseDTO obtenerPorVisita(Long idVisita) {
        Receta receta = recetaRepository.findByVisitaId(idVisita)
                .orElseThrow(() -> new RuntimeException("No hay receta para la visita con ID: " + idVisita));
        return mapearAResponseDTO(receta);
    }

    @Override
    @Transactional(readOnly = true)
    public RecetaResponseDTO obtenerPorId(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
        return mapearAResponseDTO(receta);
    }

    private RecetaResponseDTO mapearAResponseDTO(Receta r) {
        List<DetalleRecetaResponseDTO> detallesDTO = r.getDetalles().stream()
                .map(d -> new DetalleRecetaResponseDTO(
                        d.getId(),
                        d.getMedicamento().getNombre(),
                        d.getDosis()
                )).toList();

        return new RecetaResponseDTO(
                r.getId(),
                r.getVisita().getId(),
                r.getIndicacionesGenerales(),
                detallesDTO
        );
    }
}
