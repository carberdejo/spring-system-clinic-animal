package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte3 {
    private Long codigoServicio;
    private String nomServicio;
    private String nomArea;
    private int TotalSolicitudes;
    private BigDecimal TotalFacturado;

}
