package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.RecetaMaper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaUpdateDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RecetaMapperImpl implements RecetaMaper {
    @Override
    public Receta toEntity(RecetaRequestDto dto, Cita cita) {
        return Receta.builder()
//                .fechaEmision(dto.getFechaEmision())
                .indicaciones(dto.getIndicaciones())
                .medicamentos(dto.getMedicamentos())
                .cita(cita)
                .build();
    }

    @Override
    public RecetaResponseDto toDto(Receta entity) {
        return RecetaResponseDto.builder()
                .id(entity.getId())
//                .fechaEmision(entity.getFechaEmision())
                .indicaciones(entity.getIndicaciones())
                .medicamentos(entity.getMedicamentos())
                .citaId(entity.getCita().getId())
                .nombrePaciente(entity.getCita().getMascota().getNombre())
                .build();
    }

    @Override
    public void updateEntity(Receta entity, RecetaUpdateDto dto) {
        entity.setIndicaciones(dto.getIndicaciones());
        entity.setMedicamentos(dto.getMedicamentos());
    }
}
