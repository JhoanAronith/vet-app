package com.aronith.vet.service;

import com.aronith.vet.dto.request.UsuarioRequestDTO;
import com.aronith.vet.model.Usuario;
import com.aronith.vet.repository.UsuarioRepository;
import com.aronith.vet.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    @DisplayName("Debe encriptar la contraseña al guardar un usuario")
    void guardarUsuarioExitosamente() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "jhoan_test",
                "jhoantest",
                "jhoan@test.com"
        );

        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setEmail(dto.email());

        when(usuarioRepository.existsByEmail(dto.email())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("password_encriptada_abc123");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEsperado);

        usuarioService.guardar(dto);

        verify(passwordEncoder, times(1)).encode("jhoantest");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

    }

    @Test
    @DisplayName("Debe devolver una excepcion si el email ya existe")
    void emailUsuarioExiste() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "jhoan_test",
                "jhoantest",
                "jhoan@test.com"
        );

        when(usuarioRepository.existsByEmail(dto.email())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            usuarioService.guardar(dto);
        });

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe retornar true si el email existe")
    void emailUsuarioValido() {
        when(usuarioRepository.existsByEmail("jhoan@gmail.com")).thenReturn(true);
        Boolean resultado = usuarioService.existsByEmail("jhoan@gmail.com");
        assertTrue(resultado);
    }

}
