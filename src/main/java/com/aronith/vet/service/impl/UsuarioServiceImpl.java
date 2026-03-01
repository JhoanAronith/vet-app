package com.aronith.vet.service.impl;

import com.aronith.vet.dto.request.UsuarioRequestDTO;
import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.dto.response.UsuarioResponseDTO;
import com.aronith.vet.model.Usuario;
import com.aronith.vet.repository.UsuarioRepository;
import com.aronith.vet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setPassword(dto.password());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return mapearADto(usuarioGuardado);
    }

    @Override
    public Optional<UsuarioResponseDTO> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(u -> new UsuarioResponseDTO(
                        u.username(),
                        u.email()
                ));
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
