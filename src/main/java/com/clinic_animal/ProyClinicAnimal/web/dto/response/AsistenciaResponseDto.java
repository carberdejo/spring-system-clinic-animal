package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaResponseDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private EstadoAsistencia estadoAsistencia;
    private Long idPersonal;
    private String nomPersonal;

}
