package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaServicioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaServicioResponseDto;

import java.util.List;

public interface CitaServicioService {
    List<CitaServicioResponseDto>listar();
    CitaServicioResponseDto crear(CitaServicioRequestDto requestDto);
    CitaServicioResponseDto editar(Long id,CitaServicioRequestDto requestDto);
    CitaServicioResponseDto obtenerById(Long id);
    void eliminar(Long id);
}
