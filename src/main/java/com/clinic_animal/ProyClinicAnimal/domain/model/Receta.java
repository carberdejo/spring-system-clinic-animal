package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Receta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo_Receta")
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "indicaciones",length = 100)
    private String indicaciones;

    @Column(name = "medicamentos",length = 100)
    private String medicamentos;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "cita_id", unique = true,nullable = false)
    private Cita cita;

}
