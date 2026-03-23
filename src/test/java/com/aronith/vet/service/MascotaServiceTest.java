package com.aronith.vet.service;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.dto.response.MascotaResponseDTO;
import com.aronith.vet.exception.ResourceNotFoundException;
import com.aronith.vet.model.Cliente;
import com.aronith.vet.model.Mascota;
import com.aronith.vet.model.Raza;
import com.aronith.vet.repository.ClienteRepository;
import com.aronith.vet.repository.MascotaRepository;
import com.aronith.vet.repository.UsuarioRepository;
import com.aronith.vet.service.impl.MascotaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
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

}
