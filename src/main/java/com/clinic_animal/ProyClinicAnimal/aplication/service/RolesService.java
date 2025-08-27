package com.clinic_animal.ProyClinicAnimal.aplication.service;


import com.clinic_animal.ProyClinicAnimal.web.dto.request.RolesRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RolesResponseDto;

import java.util.List;

public interface RolesService {
    RolesResponseDto crear(RolesRequestDto rolesRequestDto);
    List<RolesResponseDto> listar();
    RolesResponseDto obtenerporId(Long id);
    RolesResponseDto actualizar(RolesRequestDto rolesRequestDto, Long id);
}
