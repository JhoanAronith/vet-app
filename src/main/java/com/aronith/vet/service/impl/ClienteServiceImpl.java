package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.ClienteRequestDTO;
import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.dto.response.MascotaResumenDTO;
import com.aronith.vet.model.Cliente;
import com.aronith.vet.repository.ClienteRepository;
import com.aronith.vet.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO guardar(ClienteRequestDTO dto) {

        if(clienteRepository.existsByDni(dto.dni())) {
            throw new RuntimeException("El dni ya está registrado");
        }

        if (clienteRepository.existsByTelefono(dto.telefono())) {
            throw new RuntimeException("El teléfono ya está registrado");
        }

        if (clienteRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setDni(dto.dni());
        cliente.setTelefono(dto.telefono());
        cliente.setEmail(dto.email());
        cliente.setDireccion(dto.direccion());

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return mapearADto(clienteGuardado);

    }

    @Override
    public Optional<ClienteResponseDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::mapearADto);
    }

    @Override
    public Optional<ClienteResponseDTO> buscarPorDni(String dni) {
        return clienteRepository.findByDni(dni)
                .map(this::mapearADto);
    }

    @Override
    public List<MascotaResumenDTO> listarMascotasPorCliente(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return cliente.getMascotas().stream()
                .map(m -> new MascotaResumenDTO(
                        m.getId(),
                        m.getNombre(),
                        m.getRaza().getNombre(),
                        m.getRaza().getEspecie().getNombre()
                ))
                .toList();
    }

    @Override
    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    private ClienteResponseDTO mapearADto(Cliente c) {
        return new ClienteResponseDTO(
                c.getId(),
                c.getNombre(),
                c.getApellido(),
                c.getDni(),
                c.getTelefono(),
                c.getEmail(),
                c.getDireccion()
        );
    }


}
