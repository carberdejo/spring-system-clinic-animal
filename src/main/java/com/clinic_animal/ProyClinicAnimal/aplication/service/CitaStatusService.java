package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaStatusService {
    CitaResponseDto cambiarEstado(Long id,Long idCita,CitaUpdateDTO citaUpdateDTO);
    CitaResponseDto citaEnProgreso(Long id,Long idCita);
    CitaResponseDto citaTerminada(Long id,Long idCita);
    CitaResponseDto citaReProgramada(Long idCita,Long id, LocalDateTime fecha);
    List<CitaResponseDto>listaCitasEnColaByVet(Long id);


}
