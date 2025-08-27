package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaResponseDto {
    private Long id;
    private LocalDateTime fechaEmision;
    private String indicaciones;
    private String medicamentos;
    private Long citaId;
    private String nombrePaciente;
}
