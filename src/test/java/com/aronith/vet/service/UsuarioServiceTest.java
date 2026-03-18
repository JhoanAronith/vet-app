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

        when(passwordEncoder.encode(anyString())).thenReturn("password_encriptada_abc123");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEsperado);

        usuarioService.guardar(dto);

        verify(passwordEncoder, times(1)).encode("jhoantest");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

    }

}
