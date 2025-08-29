package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.response.HistorialMedicoDto;

public interface HistorialService {
    HistorialMedicoDto obtenerHistorialPorMascota(Long idMascota);
}
