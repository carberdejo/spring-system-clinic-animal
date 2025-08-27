package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaServicioResponseDto {
    private Long id;
    private Integer cantidad;
    private double precioBase;
    private  double subTotal;
    private Long idCita;
    private Long idServicio;
    private String nomServicio;
}
