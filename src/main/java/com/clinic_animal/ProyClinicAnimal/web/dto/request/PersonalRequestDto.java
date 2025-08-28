package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalRequestDto {
    private String nombrePersonal;
    private String apellidoPersonal;
    private String contraseña;
    private int edad;
    private String dni;
    private String email;
    private String telefono;
    private Long rolCodigo;
    private Long codigoArea;
    private EstadoPersonal estadoPersonal;
}
