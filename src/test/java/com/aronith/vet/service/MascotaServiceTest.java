package com.aronith.vet.service;

import com.aronith.vet.dto.request.MascotaRequestDTO;
import com.aronith.vet.exception.ResourceNotFoundException;
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
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @InjectMocks
    private MascotaServiceImpl mascotaService;

    @Test
    @DisplayName("Debe lanzar la excepcion cuando el dueño no existe")
    void registroFallidoDuenioNoExiste() {
        MascotaRequestDTO dto = new MascotaRequestDTO("Fido", LocalDate.parse("2023-02-01"), "MACHO", 12, 4L, 200L);

        when(clienteService.obtenerPorId(200L))
                .thenThrow(new ResourceNotFoundException("Cliente no encontrado"));

        assertThrows(ResourceNotFoundException.class, () -> {
            mascotaService.guardar(dto);
        });

        verify(mascotaRepository, never()).save(any());
    }

}
