package com.aronith.vet.repository;

import com.aronith.vet.dto.response.RazaResponseDTO;
import com.aronith.vet.model.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {

    List<Raza> findByEspecieId(Long especieId);

}