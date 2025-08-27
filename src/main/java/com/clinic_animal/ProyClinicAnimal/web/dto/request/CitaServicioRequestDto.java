    package com.clinic_animal.ProyClinicAnimal.web.dto.request;

    import lombok.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CitaServicioRequestDto {
        private Integer cantidad;
        private double precioBase;
        private  double subTotal;
        private Long idCita;
        private Long idServicio;
    }
