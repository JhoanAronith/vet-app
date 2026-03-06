package com.aronith.vet.repository;

import com.aronith.vet.model.DetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
}