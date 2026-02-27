package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visitas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora = LocalDateTime.now();
    @Column
    private Double temperatura;
    @Column(name = "peso_en_visita")
    private Double pesoEnVisita;
    @Column(columnDefinition = "TEXT")
    private String diagnostico;
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;
    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL)
    private List<DetalleVisita> detalles;
}
