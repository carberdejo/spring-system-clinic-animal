package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosRequestDto {
    private String nombre;
    private String descripcion;
    private Integer duracion;
    private double precio;
    private Long idArea;
    private boolean activo;
}
