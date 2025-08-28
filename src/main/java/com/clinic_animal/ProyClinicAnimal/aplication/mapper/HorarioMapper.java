package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Horario;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HorarioResponseDto;

public interface HorarioMapper {
    Horario toEntity(HorarioRequestDto req, Roles rol);
    HorarioResponseDto toDTO(Horario res);
}
