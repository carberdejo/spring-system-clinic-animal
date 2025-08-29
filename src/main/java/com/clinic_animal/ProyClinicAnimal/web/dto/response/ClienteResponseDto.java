package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
}
