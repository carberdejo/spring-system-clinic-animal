package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;

import java.util.List;

public interface AreasService {
    AreasResponseDto crear(AreasRequestDto areasRequestDTO);
    List<AreasResponseDto> listar();
    AreasResponseDto obtenerporId(Long id);
    AreasResponseDto actualizar(AreasRequestDto areasRequestDTO,Long id);

}
