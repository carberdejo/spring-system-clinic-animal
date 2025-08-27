package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RolesRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RolesResponseDto;

public interface RolesMapper {
    Roles toEntity(RolesRequestDto dto);
    RolesResponseDto toDto(Roles Entity);
}
