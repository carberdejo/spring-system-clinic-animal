package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.PersonalResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalUpdateDto;

public interface PersonalMapper {
    Personal toEntity(PersonalRequestDto dto, Roles roles, Areas areas);
    PersonalResponseDto toDto(Personal entity);
    void PersonalUpdateDto(PersonalUpdateDto dto, Personal entity, Roles roles, Areas areas);
    void PersonalUpdateEstadoDto(PersonalUpdateEstadoDto dto, Personal entity);
}


