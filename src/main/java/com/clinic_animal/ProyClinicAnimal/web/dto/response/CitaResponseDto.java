package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CitaResponseDto {
    private Long id;
    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private Long mascotaId;
    private String nombreMascota;
    private boolean tieneReceta;


}
