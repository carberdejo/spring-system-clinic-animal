package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaRequestDto {
//    private LocalDateTime fechaEmision;
    private Integer cantidad;
    private String indicaciones;
    private String medicamentos;
    private Long citaId;
}
