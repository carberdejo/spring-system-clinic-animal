package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDto {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
}
