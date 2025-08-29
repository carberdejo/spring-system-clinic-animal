package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private Long idCliente;
    private String nomCliente;
    private Long idRecepcionista;
    private String nomRecepcionista;
    private  Long idVeterinario;
    private String nomVeterinario;
    private  Long idArea;
    private String nomArea;
    private boolean tieneReceta;
    private List<CitaServicioResponseDto> servicios;

}
