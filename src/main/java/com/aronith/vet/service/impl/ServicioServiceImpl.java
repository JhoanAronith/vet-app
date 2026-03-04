package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.ServicioRequestDTO;
import com.aronith.vet.dto.response.ServicioResponseDTO;
import com.aronith.vet.model.Servicio;
import com.aronith.vet.repository.ServicioRepository;
import com.aronith.vet.service.ServicioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioReposiroty;

    @Override
    public ServicioResponseDTO guardar(ServicioRequestDTO dto) {
        Servicio servicio = new Servicio();

        servicio.setNombre(dto.nombre());
        servicio.setDescripcion(dto.descripcion());
        servicio.setPrecio(dto.precio());

        Servicio servicioGuardado = servicioReposiroty.save(servicio);

        return mapearADto(servicioGuardado);
    }

    @Override
    public List<ServicioResponseDTO> listarTodos() {
        return servicioReposiroty.findAll()
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    private ServicioResponseDTO mapearADto(Servicio servicio) {
        return new ServicioResponseDTO(
                servicio.getId(),
                servicio.getNombre(),
                servicio.getDescripcion(),
                servicio.getPrecio()
        );
    }

}
