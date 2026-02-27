package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "razas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;
    @OneToMany(mappedBy = "raza")
    private List<Mascota> mascotas;
}
