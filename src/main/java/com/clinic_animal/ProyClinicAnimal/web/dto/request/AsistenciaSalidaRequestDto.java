package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaSalidaRequestDto {
    private LocalTime horaSalida;
}
