package com.aronith.vet.repository;

import com.aronith.vet.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Cita> findByMascotaId(long idMascota);
    List<Cita> findByEstado(String estado);
    Boolean existsByFechaHora(LocalDateTime fechaHora);

}
