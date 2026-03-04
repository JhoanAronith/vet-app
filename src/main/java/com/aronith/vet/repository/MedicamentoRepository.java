package com.aronith.vet.repository;

import com.aronith.vet.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Boolean existsByNombre(String nombre);

}
