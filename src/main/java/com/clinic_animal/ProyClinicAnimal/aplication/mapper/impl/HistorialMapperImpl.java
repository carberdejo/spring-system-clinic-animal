package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.HistorialMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HistorialMedicoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServicioHistorialDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HistorialMapperImpl implements HistorialMapper {
    @Override
    public HistorialMedicoDto toHistorialMedicoDto(Mascota m) {
        if (m == null) return null;

        return HistorialMedicoDto.builder()
                .idMascota(m.getId())
                .nombreMascota(m.getNombreMascota())
                .edad(m.getEdad())
                .sexo(m.getSexo())
                .raza(m.getRaza())
                .especie(m.getEspecie())
                .servicios(
                        m.getCitas().stream()                         // Mascota → Citas
                                .flatMap(c -> c.getCitaServicios().stream()) // Cita → CitaServicios
                                .map(cs -> cs.getServicios())                // CitaServicio → Servicio
                                .map(this::toServicioHistorialDto)           // Servicio → DTO
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ServicioHistorialDto toServicioHistorialDto(Servicios s) {
        if (s == null) return null;

        return ServicioHistorialDto.builder()
                .codigoServicio(s.getId())
                .nombreServicio(s.getNombre())
                .duracion(s.getDuracion())
                .precio(s.getPrecio())
                .build();
    }
}

