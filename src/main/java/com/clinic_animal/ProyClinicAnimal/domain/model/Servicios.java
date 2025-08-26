package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Servicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_servicio")
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer duracion;
    private double precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Codigo_Area")
    private Areas area;
}
