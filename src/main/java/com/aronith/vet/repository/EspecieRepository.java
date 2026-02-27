package com.aronith.vet.repository;

import com.aronith.vet.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

}
