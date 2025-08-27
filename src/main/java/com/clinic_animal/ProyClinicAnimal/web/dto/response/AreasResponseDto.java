package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreasResponseDto {
    private Long codigoArea;
    private String nomArea;
    private  String  descripcion;
}
