package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte1 {
    private Long id;
    private String nombre;
    private String telefono;
    private String email;
    private String nombreMascota;
    private String especie;
    private String raza;
    private Integer edad;
    private String sexo;
}
