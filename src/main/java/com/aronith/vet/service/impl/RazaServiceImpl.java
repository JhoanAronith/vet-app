package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.RazaRequestDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.model.Especie;
import com.aronith.vet.model.Raza;
import com.aronith.vet.repository.EspecieRepository;
import com.aronith.vet.repository.RazaRepository;
import com.aronith.vet.service.EspecieService;
import com.aronith.vet.service.RazaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RazaServiceImpl implements RazaService {

    private final RazaRepository razaRepository;
    private final EspecieService especieService;

    @Override
    public List<RazaResponseDTO> findAll() {
        return razaRepository.findAll().stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public RazaResponseDTO save(RazaRequestDTO dto) {
        Especie especie = especieService.obtenerPorId(dto.especieId());

        Raza raza = new Raza();
        raza.setNombre(dto.nombre());
        raza.setEspecie(especie);

        Raza razaGuardada = razaRepository.save(raza);

        return mapearADto(razaGuardada);
    }

    @Override
    public List<RazaResponseDTO> findByEspecieId(Long especieId) {
        return razaRepository.findByEspecieId(especieId).stream()
                .map(this::mapearADto)
                .toList();
    }

    private RazaResponseDTO mapearADto(Raza raza) {
        return new RazaResponseDTO(
                raza.getId(),
                raza.getNombre(),
                raza.getEspecie()!=null
                        ? raza.getEspecie().getNombre()
                        : "Sin especie"
        );
    }

}
