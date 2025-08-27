package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.AreasMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder

public class AreasMapperImpl implements AreasMapper {
    @Override
    public Areas toEntity(AreasRequestDto dto) {
        return Areas.builder()
                .nomArea(dto.getNomArea())
                .descripcion(dto.getDescripcion())
                .build();
    }

    @Override
    public AreasResponseDto toDto(Areas entity) {
        return AreasResponseDto.builder()
                .codigoArea(entity.getCodigoArea())
                .nomArea(entity.getNomArea())
                .descripcion(entity.getDescripcion())
                .build();

    }
}
