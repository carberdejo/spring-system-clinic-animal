package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaEstadoRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaSalidaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AsistenciaResponseDto;

import java.util.List;

public interface AsistenciaService {
    AsistenciaResponseDto registrarEntrada( AsistenciaRequestDto requestDto);
    List<AsistenciaResponseDto>listarById(Long id);
    List<AsistenciaResponseDto>listar(EstadoAsistencia estado,Long id);
    AsistenciaResponseDto registrarSalida(Long id);
    AsistenciaResponseDto registrarFalta(Long id);
}
