package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaMaper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CitaMapperImpl implements CitaMaper {
    @Override
    public Cita toEntity(CitaRequestDto dto, Mascota mascota) {
        return Cita.builder()
                .fechaHora(dto.getFechaHora())
                .estado(EstadoCita.EN_COLA)
                .mascota(mascota)
                .build();}

    @Override
    public CitaResponseDto toDto(Cita entity) {
        return CitaResponseDto.builder()
                .id(entity.getId())
                .fechaHora(entity.getFechaHora())
                .estado(entity.getEstado())
                .mascotaId(entity.getMascota().getId())
                .nombreMascota(entity.getMascota().getNombre())
                .tieneReceta(entity.getReceta() != null)
                .build();}
}
