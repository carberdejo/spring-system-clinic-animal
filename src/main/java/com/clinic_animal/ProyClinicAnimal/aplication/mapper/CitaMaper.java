package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;

public interface CitaMaper {
    Cita toEntity(CitaRequestDto dto, Mascota mascota);
    CitaResponseDto toDto(Cita entity);

}
