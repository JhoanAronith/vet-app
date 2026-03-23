package com.aronith.vet.service;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.dto.response.ClienteResumenDto;
import com.aronith.vet.dto.response.MascotaResponseDTO;
import com.aronith.vet.exception.ResourceNotFoundException;
import com.aronith.vet.model.Cliente;
import com.aronith.vet.model.Mascota;
import com.aronith.vet.model.Raza;
import com.aronith.vet.repository.ClienteRepository;
import com.aronith.vet.repository.MascotaRepository;
import com.aronith.vet.service.impl.MascotaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private RazaService razaService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private MascotaServiceImpl mascotaService;

    @Test
    @DisplayName("Debe lanzar la excepcion cuando el dueño no existe")
    void registroFallidoDuenioNoExiste() {
        MascotaRequestDTO dto = new MascotaRequestDTO("Fido", LocalDate.parse("2023-02-01"), "MACHO", 12, 4L, 200L);

        Raza raza = new Raza();
        raza.setId(4L);
        raza.setNombre("Bulldog");
        when(razaService.obtenerPorId(4L)).thenReturn(raza);

        when(clienteService.obtenerPorId(200L))
                .thenThrow(new ResourceNotFoundException("Cliente no encontrado"));

        assertThrows(ResourceNotFoundException.class, () -> {
            mascotaService.guardar(dto);
        });

        verify(mascotaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe guardar una mascota exitosamente cuando el dueño existe")
    void registrarMascotaExito() {
        Long idDuenio = 1L;
        Long idRaza = 4L;

        MascotaRequestDTO dto = new MascotaRequestDTO(
                "Rex",
                LocalDate.parse("2023-02-01"),
                "MACHO",
                12,
                idRaza,
                idDuenio
        );

        Raza raza = new Raza();
        raza.setId(idRaza);
        raza.setNombre("Labrador");

        Cliente duenio = new Cliente();
        duenio.setId(idDuenio);
        duenio.setNombre("Carlos");

        Mascota mascotaGuardada = new Mascota();
        mascotaGuardada.setId(10L);
        mascotaGuardada.setNombre("Rex");
        mascotaGuardada.setFechaNacimiento(LocalDate.parse("2023-02-01"));
        mascotaGuardada.setGenero("MACHO");
        mascotaGuardada.setPesoActual(12);
        mascotaGuardada.setRaza(raza);
        mascotaGuardada.setCliente(duenio);

        when(razaService.obtenerPorId(idRaza)).thenReturn(raza);
        when(clienteService.obtenerPorId(idDuenio)).thenReturn(duenio);
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascotaGuardada);

        MascotaResponseDTO resultado = mascotaService.guardar(dto);

        assertNotNull(resultado);
        assertEquals("Rex",       resultado.nombre());
        assertEquals("Labrador",  resultado.nombreRaza());
        assertEquals("Carlos",    resultado.nombreCliente());

        verify(mascotaRepository, times(1)).save(any(Mascota.class));

        verify(razaService,    times(1)).obtenerPorId(idRaza);
        verify(clienteService, times(1)).obtenerPorId(idDuenio);
    }

    @Test
    @DisplayName("Debe lanzar una excepción cuando la raza no existe")
    void registroFallidoRazaNoExiste() {
        MascotaRequestDTO dto = new MascotaRequestDTO("Fido", LocalDate.parse("2023-02-01"), "MACHO", 12, 10L, 200L);


        when(razaService.obtenerPorId(10L)).thenThrow(new ResourceNotFoundException("Raza no encontrada"));

        assertThrows(ResourceNotFoundException.class, () -> {
            mascotaService.guardar(dto);
        });

        verify(mascotaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe retornar una lista con mascotas")
    void listarTodasMascotas() {
        Raza raza = new Raza();
        raza.setNombre("Labrador");

        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");

        Mascota mascota1 = new Mascota();
        mascota1.setId(1L);
        mascota1.setNombre("Rex");
        mascota1.setRaza(raza);
        mascota1.setCliente(cliente);

        Mascota mascota2 = new Mascota();
        mascota2.setId(2L);
        mascota2.setNombre("Fido");
        mascota2.setRaza(raza);
        mascota2.setCliente(cliente);

        List<Mascota> mascotas = List.of(mascota1, mascota2);

        when(mascotaRepository.findAll()).thenReturn(mascotas);

        List<MascotaResponseDTO> resultado = mascotaService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(mascotaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe devolver una lista vacia")
    void listarMascotasVacio() {
        when(mascotaRepository.findAll()).thenReturn(List.of());
        List<MascotaResponseDTO> resultado = mascotaService.listarTodos();
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe devolver una mascota buscada por su nombre")
    void buscarMascotaPorNombre() {
        Raza raza = new Raza();
        raza.setNombre("Labrador");

        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");

        Mascota mascota = new Mascota();
        mascota.setId(1L);
        mascota.setNombre("Fido");
        mascota.setRaza(raza);
        mascota.setCliente(cliente);

        when(mascotaRepository.findByNombre("Fido")).thenReturn(Optional.of(mascota));

        List<MascotaResponseDTO> resultado = mascotaService.buscarPorNombre("Fido");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Fido", resultado.get(0).nombre());
        verify(mascotaRepository, times(1)).findByNombre("Fido");
    }

    @Test
    @DisplayName("Debe devolver una mascota buscada por su nombre")
    void buscarMascotaPorNombreFallido() {
        when(mascotaRepository.findByNombre("Fido")).thenReturn(Optional.empty());
        List<MascotaResponseDTO> resultado = mascotaService.buscarPorNombre("Fido");
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe devolver el dueño de una mascota buscada por su id")
    void buscarClientePorMascota() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jhoan");
        cliente.setDni("74640216");
        cliente.setTelefono("930473294");

        Mascota mascota = new Mascota();
        mascota.setId(1L);
        mascota.setCliente(cliente);

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));

        Optional<ClienteResumenDto> resultado = mascotaService.buscarClientePorMascotaId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Jhoan", resultado.get().nombre());
    }

    @Test
    @DisplayName("Debe devolver un Optional.empty()")
    void buscarClientePorMascotaNoExiste() {
        when(mascotaRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<ClienteResumenDto> resultado = mascotaService.buscarClientePorMascotaId(1L);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe devolver una mascota buscada por su id")
    void buscarMascotaPorId() {
        Raza raza = new Raza();
        raza.setId(1L);
        raza.setNombre("Labrador");

        Cliente duenio = new Cliente();
        duenio.setId(1L);
        duenio.setNombre("Carlos");

        Mascota mascotaGuardada = new Mascota();
        mascotaGuardada.setId(1L);
        mascotaGuardada.setNombre("Rex");
        mascotaGuardada.setFechaNacimiento(LocalDate.parse("2023-02-01"));
        mascotaGuardada.setGenero("MACHO");
        mascotaGuardada.setPesoActual(12);
        mascotaGuardada.setRaza(raza);
        mascotaGuardada.setCliente(duenio);

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascotaGuardada));

        Mascota resultado = mascotaService.buscarMascotaPorId(1L);

        assertNotNull(resultado);
        assertEquals("Rex", resultado.getNombre());
    }

}
