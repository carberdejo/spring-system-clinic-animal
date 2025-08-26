package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CitaServicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_citaServ")
    private Long id;
    private Integer cantidad;
    private double precioBase;
    private  double subTotal;
    @ManyToOne
    @JoinColumn(name="Codigo_Area")
    private Cita cita;
    @ManyToOne
    @JoinColumn(name="cod_servicio")
    private Servicios servicios;
}
