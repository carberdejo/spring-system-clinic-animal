package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesRequestDto {
    private String rolNombre;
    private String descripcion;
}
