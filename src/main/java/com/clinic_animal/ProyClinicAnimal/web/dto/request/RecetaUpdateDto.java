package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaUpdateDto {
    private String indicaciones;
    private Integer cantidad;
    private String medicamentos;
}
