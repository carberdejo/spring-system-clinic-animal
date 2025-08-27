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
    @Column(name = "id_cita_Serv")
    private Long id;
    private Integer cantidad;
    @Column(name = "precio_Base")
    private double precioBase;
    @Column(name = "sub_Total")
    private  double subTotal;
    @ManyToOne
    @JoinColumn(name="id_cita")
    private Cita cita;
    @ManyToOne
    @JoinColumn(name="cod_servicio")
    private Servicios servicios;
}
