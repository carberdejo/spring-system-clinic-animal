package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;

import java.util.List;

public interface CitaService {
    CitaResponseDto crear(CitaRequestDto citaRequestDTO);
    CitaResponseDto obtenerPorId(Long id);
    List<CitaResponseDto> listar();
    List<CitaResponseDto> listarPorMascota(Long mascotaId);
    List<CitaResponseDto> listarPorEstado(EstadoCita estado);
    CitaResponseDto actualizarEstado(Long id, CitaUpdateDTO citaUpdateDTO);

}
