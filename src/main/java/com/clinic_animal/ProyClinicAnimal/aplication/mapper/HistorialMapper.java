package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HistorialMedicoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServicioHistorialDto;

public interface HistorialMapper {
    HistorialMedicoDto toHistorialMedicoDto(Mascota m);
    ServicioHistorialDto toServicioHistorialDto(Servicios s);

}
