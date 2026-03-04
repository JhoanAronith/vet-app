package com.aronith.vet.repository;

import com.aronith.vet.model.DetalleVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVisitaRepository extends JpaRepository<DetalleVisita, Long> {

    List<DetalleVisita> findByMedicamentoId(Long idMedicamento);
    List<DetalleVisita> findByServicioId(Long idServicio);
    List<DetalleVisita> findByVisitaId(Long idVisita);

}
