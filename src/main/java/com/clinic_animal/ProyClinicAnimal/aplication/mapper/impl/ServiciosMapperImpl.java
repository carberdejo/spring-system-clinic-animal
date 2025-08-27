package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.ServiciosMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServiciosResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ServiciosMapperImpl implements ServiciosMapper {
    @Override
    public Servicios toEntity(ServiciosRequestDto req, Areas area) {
        return Servicios.builder()
                .nombre(req.getNombre())
                .descripcion(req.getDescripcion())
                .duracion(req.getDuracion())
                .precio(req.getPrecio())
                .area(area)
                .build();
    }

    @Override
    public ServiciosResponseDto toDTO(Servicios res) {
        return ServiciosResponseDto.builder()
                .id(res.getId())
                .descripcion(res.getDescripcion())
                .nombre(res.getNombre())
                .duracion(res.getDuracion())
                .precio(res.getPrecio())
                .idArea(res.getArea().getCodigoArea())
                .nomArea(res.getArea().getNomArea())
                .build();
    }
}
