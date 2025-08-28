package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoHorario;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioResponseDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private EstadoHorario estadoHorario;
    private Long idRol;
    private String nameRol;
}
