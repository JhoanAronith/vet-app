package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "recetas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "indicaciones_generales", columnDefinition = "TEXT")
    private String indicacionesGenerales;
    @OneToOne
    @JoinColumn(nullable = false, unique = true, name = "visita_id")
    private Visita visita;
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<DetalleReceta> detalles;
}
