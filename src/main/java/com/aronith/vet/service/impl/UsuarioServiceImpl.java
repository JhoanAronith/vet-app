package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.UsuarioRequestDTO;
import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.dto.response.UsuarioResponseDTO;
import com.aronith.vet.model.Usuario;
import com.aronith.vet.repository.UsuarioRepository;
import com.aronith.vet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        String passwordEncriptada = passwordEncoder.encode(dto.password());
        usuario.setPassword(passwordEncriptada);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return mapearADto(usuarioGuardado);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    private UsuarioResponseDTO mapearADto(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getUsername(),
                usuario.getEmail()
        );
    }

}
