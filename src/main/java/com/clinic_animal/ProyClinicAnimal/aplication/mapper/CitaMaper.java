package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.*;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;

public interface CitaMaper {
    Cita toEntity(CitaRequestDto dto, Mascota mascota, Cliente cliente, Personal recep, Personal vet, Areas areas);
    CitaResponseDto toDto(Cita entity);

}
