package com.aronith.vet.repository;

import com.aronith.vet.dto.response.ClienteResponseDTO;
import com.aronith.vet.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Boolean existsByDni(String dni);
    Boolean existsByTelefono(String telefono);
    Boolean existsByEmail(String email);
    Optional<Cliente> findByDni(String dni);

}
