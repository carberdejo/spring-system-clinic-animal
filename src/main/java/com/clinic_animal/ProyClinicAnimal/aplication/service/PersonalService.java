package com.clinic_animal.ProyClinicAnimal.aplication.service;


import com.clinic_animal.ProyClinicAnimal.web.dto.request.*;

import com.clinic_animal.ProyClinicAnimal.web.dto.response.PersonalResponseDto;

import java.util.List;

public interface PersonalService {
    PersonalResponseDto crear(PersonalRequestDto personalRequestDto);
    List<PersonalResponseDto> listar();
    PersonalResponseDto obtenerporId(Long id);
    List<PersonalResponseDto> obtenerporRolCodigo(Long rolCodigo);
    List<PersonalResponseDto> obtenerporCodigoArea(Long codigoArea);
    PersonalResponseDto actualizar(PersonalUpdateDto personalUpdateDto, Long id);
    PersonalResponseDto actualizarEstado(PersonalUpdateEstadoDto personalUpdateEstadoDto, Long id);
    PersonalResponseDto actualizarRoles(PersonalUpdateRolesDto personalUpdateRolesDto, Long id);
    PersonalResponseDto finalizarDescanso(PersonalRemoverDescansoDto personalRemoverDescansoDto, Long id);
}
