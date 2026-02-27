package com.aronith.vet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column(nullable = false,unique = true, length = 8)
    private String dni;
    @Column(nullable = false,unique = true, length = 9)
    private String telefono;
    @Column(nullable = false,unique = true, length = 100)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String direccion;
    @OneToMany(mappedBy = "cliente")
    private List<Mascota> mascotas;
}
