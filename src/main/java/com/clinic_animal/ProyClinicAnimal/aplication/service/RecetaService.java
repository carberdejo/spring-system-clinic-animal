package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaUpdateDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RecetaService {
    RecetaResponseDto crear(RecetaRequestDto recetaRequestDTO);
    RecetaResponseDto obtenerPorId(Long id);
    List<RecetaResponseDto> listar();
    List<RecetaResponseDto> buscarPorNombre(String nombre); // similar al repo
//    List<RecetaResponseDto> buscarPorFecha(LocalDateTime inicio, LocalDateTime fin);
    RecetaResponseDto actualizar(Long id, RecetaUpdateDto recetaUpdateDTO);
    void eliminar(Long id);
}
