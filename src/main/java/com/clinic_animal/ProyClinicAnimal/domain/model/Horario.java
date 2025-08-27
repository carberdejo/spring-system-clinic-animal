package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "Horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_horario")
    private Long id;
    private LocalDate fecha;
    @Column(name="hora_entrada")
    private LocalTime horaEntrada;
    @Column(name="hora_salida")
    private LocalTime horaSalida;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "Codigo_Rol")
    private Roles roles;
}
