package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;

public interface AreasMapper {
    Areas toEntity(AreasRequestDto dto);
    AreasResponseDto toDto(Areas entity);
}
