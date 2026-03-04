package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.MedicamentoRequestDTO;
import com.aronith.vet.dto.response.MedicamentoResponseDTO;
import com.aronith.vet.model.Medicamento;
import com.aronith.vet.repository.MedicamentoRepository;
import com.aronith.vet.service.MedicamentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    @Override
    public MedicamentoResponseDTO guardar(MedicamentoRequestDTO dto) {
        if(medicamentoRepository.existsByNombre(dto.nombre())) {
            throw new RuntimeException("Ya existe un medicamento con el mismo nombre");
        }

        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(dto.nombre());
        medicamento.setStock(dto.stock());
        medicamento.setPrecioVenta(dto.precioVenta());

        Medicamento medicamentoGuardado = medicamentoRepository.save(medicamento);

        return mapearADto(medicamentoGuardado);
    }

    @Override
    public List<MedicamentoResponseDTO> listar() {
        return medicamentoRepository.findAll()
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    private MedicamentoResponseDTO mapearADto(Medicamento medicamento) {
        return new MedicamentoResponseDTO(
                medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getStock(),
                medicamento.getPrecioVenta()
        );
    }

}
