package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Codigo_Rol")
    private Long rolCodigo;
    @Column(name = "Nombre_Rol")
    private String rolNombre;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name="Acceso_Web")
    private boolean accesoWeb;
    @OneToMany(mappedBy = "roles",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Personal> personalList;
}
