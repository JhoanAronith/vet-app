package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicamentos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
    @Column(nullable = false)
    private Integer stock = 0;
    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta = 0.0;
}
