package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioUpdateRequestDto {
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
}
