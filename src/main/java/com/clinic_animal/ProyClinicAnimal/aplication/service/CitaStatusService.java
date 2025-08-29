package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaReprogramarDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaStatusService {
    CitaResponseDto cambiarEstado(String email,Long idCita,CitaUpdateDTO citaUpdateDTO);
    CitaResponseDto citaEnProgreso(String email,Long idCita);
    CitaResponseDto citaTerminada(String email,Long idCita);
    CitaResponseDto citaReProgramada(String email,Long idCita, CitaReprogramarDto fecha);
    List<CitaResponseDto>listaCitasEnColaByVet(Long id);


}
