package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteUpdateRequestDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;
}
