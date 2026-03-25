package com.aronith.vet.service;

import com.aronith.vet.dto.request.EspecieRequestDTO;
import com.aronith.vet.dto.response.EspecieResponseDTO;
import com.aronith.vet.model.*;
import com.aronith.vet.repository.EspecieRepository;
import com.aronith.vet.service.impl.EspecieServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EspecieServiceTest {

    @Mock
    private EspecieRepository especieRepository;

    @InjectMocks
    private EspecieServiceImpl especieService;

    @Test
    @DisplayName("Debe guardar exitosamente una especie y retornar DTO")
    void guardarEspecieExitoso() {
        EspecieRequestDTO dto = new EspecieRequestDTO("Reptiles");

        Especie especieGuardada = new Especie();
        especieGuardada.setId(1L);
        especieGuardada.setNombre("Reptiles");
        especieGuardada.setRazas(List.of());

        when(especieRepository.save(any(Especie.class))).thenReturn(especieGuardada);

        EspecieResponseDTO resultado = especieService.guardar(dto);

        assertNotNull(resultado);
        assertEquals("Reptiles", resultado.nombre());
        verify(especieRepository, times(1)).save(any(Especie.class));
    }

}
