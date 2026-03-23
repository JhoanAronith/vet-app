package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.dto.response.ClienteResumenDto;
import com.aronith.vet.dto.response.MascotaResponseDTO;
import com.aronith.vet.exception.ResourceNotFoundException;
import com.aronith.vet.model.Cliente;
import com.aronith.vet.model.Mascota;
import com.aronith.vet.model.Raza;
import com.aronith.vet.repository.MascotaRepository;
import com.aronith.vet.service.ClienteService;
import com.aronith.vet.service.MascotaService;
import com.aronith.vet.service.RazaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final RazaService razaService;
    private final ClienteService clienteService;

    @Override
    public MascotaResponseDTO guardar(MascotaRequestDTO dto) {

        Raza raza = razaService.obtenerPorId(dto.razaId());
        Cliente cliente = clienteService.obtenerPorId(dto.clienteId());

        Mascota mascota = new Mascota();
        mascota.setNombre(dto.nombre());
        mascota.setFechaNacimiento(dto.fechaNacimiento());
        mascota.setGenero(dto.genero());
        mascota.setPesoActual(dto.pesoActual());
        mascota.setRaza(raza);
        mascota.setCliente(cliente);

        Mascota mascotaGuardada = mascotaRepository.save(mascota);

        return mapearADto(mascotaGuardada);
    }

    @Override
    public List<MascotaResponseDTO> listarTodos() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public List<MascotaResponseDTO> buscarPorNombre(String nombre) {
        return mascotaRepository.findByNombre(nombre)
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public Optional<ClienteResumenDto> buscarClientePorMascotaId(Long id) {
        return mascotaRepository.findById(id)
                .map(mascota -> {
                    Cliente c = mascota.getCliente();
                    return new ClienteResumenDto(
                            c.getNombre(),
                            c.getDni(),
                            c.getTelefono()
                    );
                });
    }

    @Override
    public Mascota buscarMascotaPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Mascota no encontrada"));
    }

    private MascotaResponseDTO mapearADto(Mascota mascota) {
        return new MascotaResponseDTO(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getFechaNacimiento(),
                mascota.getGenero(),
                mascota.getPesoActual(),
                mascota.getRaza().getNombre(),
                mascota.getCliente().getNombre()
        );
    }

}
