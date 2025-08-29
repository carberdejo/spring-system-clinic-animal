package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioHistorialDto {
    private Long codigoServicio;
    private String nombreServicio;
    private Integer duracion;
    private double precio;
}
