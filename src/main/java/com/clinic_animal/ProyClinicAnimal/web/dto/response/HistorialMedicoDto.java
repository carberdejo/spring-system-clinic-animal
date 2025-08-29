package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialMedicoDto {
    private Long idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;
    private Integer edad;
    private String sexo;
    private Long idCliente;
    private String nombreCliente;
    private String dniCliente;
    private Integer totalCitas;
    private LocalDateTime fechaPrimeraCita;
    private LocalDateTime fechaUltimaCita;

}
