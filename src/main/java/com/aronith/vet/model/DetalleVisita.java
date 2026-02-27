package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_visita")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DetalleVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer cantidad = 1;
    @Column(name = "precio_aplicado", nullable = false)
    private Double precioAplicado;
    @ManyToOne
    @JoinColumn(name = "visita_id", nullable = false)
    private Visita visita;
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;
}