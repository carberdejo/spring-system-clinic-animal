package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServiciosResponseDto;

public interface ServiciosMapper {
    Servicios toEntity(ServiciosRequestDto req, Areas area);
    ServiciosResponseDto toDTO(Servicios res);
     void ServiciosUpdateEstadoDto(ServiciosUpdateEstadoDto dto, Servicios entity);
}
