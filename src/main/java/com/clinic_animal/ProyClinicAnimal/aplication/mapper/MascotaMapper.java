package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.MascotaResponseDto;

public interface MascotaMapper {
    Mascota toEntity(MascotaRequestDto dto);
    void updateEntity(Mascota mascota, MascotaUpdateRequestDto dto);
    MascotaResponseDto toResponse(Mascota mascota);
}
