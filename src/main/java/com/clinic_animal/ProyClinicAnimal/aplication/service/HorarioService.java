package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HorarioResponseDto;


import java.util.List;


public interface HorarioService {
    List<HorarioResponseDto>listar();
    List<HorarioResponseDto>listaHorarioByRol(Long id);
    HorarioResponseDto obtenerById(Long id);
    HorarioResponseDto crear(HorarioRequestDto  requestDto);
    HorarioResponseDto UpdateHoras(Long id,HorarioUpdateRequestDto updateRequestDto);
}
