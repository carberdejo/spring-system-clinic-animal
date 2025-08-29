package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MascotaUpdateRequestDto {
    private String nombre;
    private String raza;
    private String especie;
    private Integer edad;
    private String sexo;
    private Long idCliente;
}
