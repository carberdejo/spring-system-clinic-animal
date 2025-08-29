package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServiciosResponseDto;

import java.util.List;

public interface ServiciosService {
    List<ServiciosResponseDto>lista();
    ServiciosResponseDto obtenerById(Long id);
    ServiciosResponseDto crear(ServiciosRequestDto requestDto);
    ServiciosResponseDto editar(Long id ,ServiciosRequestDto requestDto);
    ServiciosResponseDto deshabilitar(Long id, ServiciosUpdateEstadoDto serviciosUpdateEstadoDto);}
