package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaServicioMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.CitaServicio;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaServicioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaServicioResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CitaServicioMapperImpl implements CitaServicioMapper {
    @Override
    public CitaServicio toEntity(CitaServicioRequestDto req, Cita cita, Servicios servicios) {
        return CitaServicio.builder()
                .cantidad(req.getCantidad())
                .precioBase(req.getPrecioBase())
                .subTotal( req.getCantidad()* req.getPrecioBase() )
                .cita(cita)
                .servicios(servicios)
                .build();
    }

    @Override
    public CitaServicioResponseDto toDTO(CitaServicio res) {
        return CitaServicioResponseDto.builder()
                .id(res.getId())
                .idServicio(res.getServicios().getId())
                .nomServicio(res.getServicios().getNombre())
                .idCita(res.getCita().getId())
                .cantidad(res.getCantidad())
                .precioBase(res.getPrecioBase())
                .subTotal(res.getSubTotal())
                .build();
    }
}
