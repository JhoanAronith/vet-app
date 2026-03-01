package com.aronith.vet.service;

import com.aronith.vet.dto.request.UsuarioRequestDTO;
import com.aronith.vet.dto.response.UsuarioResponseDTO;
import java.util.Optional;

public interface UsuarioService {

    UsuarioResponseDTO guardar(UsuarioRequestDTO dto);
    Optional<UsuarioResponseDTO> findByEmail(String email);
    Boolean existsByEmail(String email);

}
