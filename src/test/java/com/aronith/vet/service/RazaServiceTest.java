package com.aronith.vet.service;

import com.aronith.vet.dto.request.RazaRequestDTO;
import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.model.Especie;
import com.aronith.vet.model.Raza;
import com.aronith.vet.repository.RazaRepository;
import com.aronith.vet.service.impl.RazaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RazaServiceTest {

    @Mock
    RazaRepository razaRepository;

    @Mock
    private EspecieService especieService;

    @InjectMocks
    RazaServiceImpl razaService;

    @Test
    @DisplayName("Debe guardar exitosamente una especie y retornar un DTO")
    void guardarRazaExitoso() {
        RazaRequestDTO dto = new RazaRequestDTO("Doberman", 1L);

        Especie especie = new Especie();
        especie.setId(1L);
        especie.setNombre("Canes");

        when(especieService.obtenerPorId(1L)).thenReturn(especie);

        Raza razaGuardada = new Raza();
        razaGuardada.setId(1L);
        razaGuardada.setNombre(dto.nombre());


        when(razaRepository.save(any(Raza.class))).thenReturn(razaGuardada);

        RazaResponseDTO resultado = razaService.save(dto);

        assertNotNull(resultado);
        assertEquals("Doberman", resultado.nombre());
        verify(razaRepository, times(1)).save(any(Raza.class));
    }

}
