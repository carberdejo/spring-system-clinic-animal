package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaReprogramarDto {
    private LocalDateTime fecha;
}
