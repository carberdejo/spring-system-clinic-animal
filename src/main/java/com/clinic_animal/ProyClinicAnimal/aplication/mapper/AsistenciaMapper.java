package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Asistencia;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AsistenciaResponseDto;

public interface AsistenciaMapper {
    Asistencia toEntity(AsistenciaRequestDto req, Personal personal);
    AsistenciaResponseDto toDTO(Asistencia res);
}
