package com.aronith.vet.repository;

import com.aronith.vet.dto.response.UsuarioResponseDTO;
import com.aronith.vet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

}
