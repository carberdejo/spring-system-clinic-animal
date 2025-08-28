package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaRequestDto {
    private EstadoAsistencia estadoAsistencia;
    private Long idPersonal;
}
