package com.aronith.vet.repository;

import com.aronith.vet.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {

    List<Visita> findByMascotaIdOrderByFechaHoraDesc(Long idMascota);
    List<Visita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Visita> findByMascotaClienteId(Long idCliente);

}
