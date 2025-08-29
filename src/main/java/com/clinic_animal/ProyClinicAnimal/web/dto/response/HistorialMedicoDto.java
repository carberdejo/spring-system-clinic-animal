package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialMedicoDto {
    private Long idMascota;
    private String nombreMascota;
    private int edad;
    private String sexo;
    private String raza;
    private String especie;

    private List<ServicioHistorialDto> servicios;
}
