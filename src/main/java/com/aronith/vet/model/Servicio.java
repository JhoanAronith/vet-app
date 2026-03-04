package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @Column(nullable = false)
    private Double precio = 0.00;
}
