package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalUpdateDto {

    private String nombreRol;
    private String nomArea;
    private String email;
    private String telefono;

}
