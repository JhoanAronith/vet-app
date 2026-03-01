package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.EspecieRequestDTO;
import com.aronith.vet.dto.response.EspecieResponseDTO;
import com.aronith.vet.model.Especie;
import com.aronith.vet.model.Raza;
import com.aronith.vet.repository.EspecieRepository;
import com.aronith.vet.service.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository repository;

    @Override
    public EspecieResponseDTO guardar(EspecieRequestDTO dto) {
        Especie especie = new Especie();
        especie.setNombre(dto.nombre());

        Especie especieGuardada = repository.save(especie);
        return mapearADto(especieGuardada);
    }

    @Override
    public List<EspecieResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public Especie obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));
    }

    private EspecieResponseDTO mapearADto(Especie especie) {
        List<String> nombres = (especie.getRazas() != null)
                ? especie.getRazas().stream().map(Raza::getNombre).toList()
                : List.of();
        return  new EspecieResponseDTO(
                especie.getId(),
                especie.getNombre(),
                nombres
        );
    }

}
