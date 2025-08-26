package com.clinic_animal.ProyClinicAnimal.domain.model;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Asistencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_asistencia")
    private Long id;
    private LocalDate fecha;
    @Column(name="hora_entrada")
    private LocalTime horaEntrada;
    @Column(name="hora_salida")
    private LocalTime horaSalida;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoAsistencia estadoAsistencia;
    @ManyToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;
}
