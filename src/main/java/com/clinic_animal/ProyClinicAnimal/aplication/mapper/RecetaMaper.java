package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaUpdateDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;

public interface RecetaMaper {
    Receta toEntity(RecetaRequestDto dto, Cita cita);
    RecetaResponseDto toDto(Receta entity);
    void updateEntity(Receta entity, RecetaUpdateDto dto);

}
