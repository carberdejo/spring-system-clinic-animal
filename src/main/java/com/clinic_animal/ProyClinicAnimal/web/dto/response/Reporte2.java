package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte2 {
    private Long id_cita;
    private LocalDateTime fechaHora;
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String nomMascota;
    private String nombreServicio;
    private Integer cantidad;
    private double precioBase;
    private double subTotal;
    private String nomArea;
}
