package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalResponseDto {
    private Long id;
    private String nombrePersonal;
    private String apellidoPersonal;
    private int edad;
    private String dni;
    private String email;
    private String telefono;
    private Long rolCodigo;
    private String nombreRol;
    private Long codigoArea;
    private String nomArea;
    private LocalDate fechaRegistro;
    private EstadoPersonal estadoPersonal;
}
