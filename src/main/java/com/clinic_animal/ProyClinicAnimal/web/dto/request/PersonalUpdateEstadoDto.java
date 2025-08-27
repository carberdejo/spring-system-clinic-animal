package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalUpdateEstadoDto {
    private EstadoPersonal estadoPersonal;
}
