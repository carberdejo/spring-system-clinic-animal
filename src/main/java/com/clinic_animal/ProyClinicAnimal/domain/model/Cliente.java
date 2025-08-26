package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id ;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="dni")
    private String dni ;
    @Column(name="telefono")
    private String telefono;
    @Column(name="direccion")
    private String direccion;
    @Column(name="email")
    private String email;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Mascota> mascotas;
}
