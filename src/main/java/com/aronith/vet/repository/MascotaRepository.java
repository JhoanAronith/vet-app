package com.aronith.vet.repository;

import com.aronith.vet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    Optional<Mascota> findByNombre(String nombre);

}
