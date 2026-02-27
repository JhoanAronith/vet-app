package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mascotas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Column(length = 20)
    private String genero;
    @Column(name = "peso_actual")
    private double pesoActual;
    @ManyToOne
    @JoinColumn(name = "raza_id", nullable = false)
    private Raza raza;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "mascota")
    private List<Cita> citas;
    @OneToMany(mappedBy = "mascota")
    private List<Visita> visitas;

}
