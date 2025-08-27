package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.CitaServicio;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaServicioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaServicioResponseDto;

public interface CitaServicioMapper {
    CitaServicio toEntity(CitaServicioRequestDto req, Cita cita, Servicios servicios);
    CitaServicioResponseDto toDTO(CitaServicio res);
}
