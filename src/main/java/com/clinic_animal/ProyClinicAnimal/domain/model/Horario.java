package com.clinic_animal.ProyClinicAnimal.domain.model;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoHorario;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoHorario estadoHorario;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_rol")
    private Roles roles;
}
