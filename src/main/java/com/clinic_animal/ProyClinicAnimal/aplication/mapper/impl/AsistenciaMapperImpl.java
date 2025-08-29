package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.AsistenciaMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Asistencia;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AsistenciaResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class AsistenciaMapperImpl implements AsistenciaMapper {
    @Override
    public Asistencia toEntity(AsistenciaRequestDto req, Personal personal) {
        return Asistencia.builder()

                .fecha(LocalDate.now())
                .horaEntrada(LocalTime.now().withNano(0))
                .horaSalida(LocalTime.of(0,0))
                .personal(personal)
                .build();
    }

    @Override
    public AsistenciaResponseDto toDTO(Asistencia res) {
        return AsistenciaResponseDto.builder()
                .id(res.getId())
                .estadoAsistencia(res.getEstadoAsistencia())
                .fecha(res.getFecha())
                .horaEntrada(res.getHoraEntrada())
                .horaSalida(res.getHoraSalida())
                .idPersonal(res.getPersonal().getId())
                .nomPersonal(res.getPersonal().getNombrePersonal() +" " +res.getPersonal().getApellidoPersonal())
                .build();
    }
}
