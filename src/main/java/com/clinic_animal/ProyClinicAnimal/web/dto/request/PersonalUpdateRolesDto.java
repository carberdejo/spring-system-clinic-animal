package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalUpdateRolesDto {
    private String contraseña;
    private Long rolCodigo;
    private Long codigoArea;
}
