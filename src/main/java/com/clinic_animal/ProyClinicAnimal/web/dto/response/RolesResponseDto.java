package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesResponseDto {
    private Long rolCodigo;
    private String rolNombre;
    private String descripcion;
}
