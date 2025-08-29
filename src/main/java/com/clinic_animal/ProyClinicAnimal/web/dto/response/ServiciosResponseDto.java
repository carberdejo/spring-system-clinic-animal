package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer duracion;
    private double precio;
    private Long idArea;
    private String nomArea;
    private boolean activo;
}
