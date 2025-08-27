package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.AreasMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.RolesMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RolesRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RolesResponseDto;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class RolesMapperImpl implements RolesMapper {

    @Override
    public Roles toEntity(RolesRequestDto dto) {
        return Roles.builder()
                .rolNombre(dto.getRolNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    @Override
    public RolesResponseDto toDto(Roles Entity) {
        return RolesResponseDto.builder()
                .rolCodigo(Entity.getRolCodigo())
                .rolNombre(Entity.getRolNombre())
                .descripcion(Entity.getDescripcion())
                .build();
    }
}
