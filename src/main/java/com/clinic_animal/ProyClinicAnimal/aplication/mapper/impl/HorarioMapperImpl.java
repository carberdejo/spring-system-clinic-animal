package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.HorarioMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Horario;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoHorario;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HorarioResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapperImpl implements HorarioMapper {
    @Override
    public Horario toEntity(HorarioRequestDto req, Roles rol) {
        return Horario.builder()
                .fecha(req.getFecha())
                .horaEntrada(req.getHoraEntrada())
                .horaSalida(req.getHoraSalida())
                .roles(rol)
                .estadoHorario(EstadoHorario.DISPONIBLE)
                .build();
    }

    @Override
    public HorarioResponseDto toDTO(Horario res) {
        return HorarioResponseDto.builder()
                .id(res.getId())
                .fecha(res.getFecha())
                .horaEntrada(res.getHoraEntrada())
                .horaSalida(res.getHoraSalida())
                .idRol(res.getRoles().getRolCodigo())
                .nameRol(res.getRoles().getRolNombre())
                .estadoHorario(res.getEstadoHorario())
                .build();
    }
}
