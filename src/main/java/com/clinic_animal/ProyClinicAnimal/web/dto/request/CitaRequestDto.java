package com.clinic_animal.ProyClinicAnimal.web.dto.request;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaRequestDto {
    private Long mascotaId;
    private Long idCliente;
    private Long idRecepcionista;
    private  Long idVeterinario;
    private Long idArea;
    private EstadoCita estadoCita;

}
