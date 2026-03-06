package com.aronith.vet.repository;

import com.aronith.vet.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    Optional<Receta> findByVisitaId(Long visitaId);
}
