package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.AsistenciaMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.AsistenciaService;
import com.clinic_animal.ProyClinicAnimal.aplication.service.HorarioService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Asistencia;
import com.clinic_animal.ProyClinicAnimal.domain.model.Horario;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import com.clinic_animal.ProyClinicAnimal.domain.repository.AsistenciaRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.HorarioRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.PersonalRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaEstadoRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AsistenciaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {
    private final AsistenciaMapper asistenciaMapper;
    private final AsistenciaRepository asistenciaRepository;
    private  final PersonalRepository personalRepository;
    private final HorarioRepository horarioRepository;

    @Transactional
    @Override
    public AsistenciaResponseDto registrarEntrada(AsistenciaRequestDto requestDto) {
        Personal personal = personalRepository.findById(requestDto.getIdPersonal()).orElseThrow(()->
                new ErrorNegocio("El personal con ID "+requestDto.getIdPersonal()+" no existe"));

        if(asistenciaRepository.existsByFechaAndPersonalId(LocalDate.now(),requestDto.getIdPersonal())){
            throw new ErrorNegocio("No puedes registrar la asistencia de presencia 2 veces en un dia");
        }
        Horario horario = horarioRepository.findById(personal.getAreas().getCodigoArea()).orElseThrow(
                ()->new ErrorNegocio("El area del personal no tiene un horario"));

        Asistencia asistencia = asistenciaMapper.toEntity(requestDto,personal);

        LocalTime horaEntradaMaximo = horario.getHoraEntrada().plusMinutes(30);

        if(asistencia.getHoraEntrada().isAfter(horaEntradaMaximo)){
            asistencia.setEstadoAsistencia(EstadoAsistencia.TARDANZA);
        }else{
            asistencia.setEstadoAsistencia(EstadoAsistencia.PRESENTE);
        }

        return asistenciaMapper.toDTO(asistenciaRepository.save(asistencia));
    }

    @Override
    public List<AsistenciaResponseDto> listarById(Long id) {
        return asistenciaRepository.findAllByPersonalId(id).stream()
                .map(asistenciaMapper::toDTO).toList();
    }

    @Override
    public List<AsistenciaResponseDto> listar(EstadoAsistencia estado,Long id) {
        if(estado != null && id != null){
            return asistenciaRepository.findAllByEstadoAsistenciaAndPersonalId
                        (estado,id).stream().map(asistenciaMapper::toDTO).toList();
        } else if (estado != null) {
            return asistenciaRepository.findAllByEstadoAsistencia(estado)
                    .stream().map(asistenciaMapper::toDTO).toList();
        }else if (id  != null){
            return asistenciaRepository.findAllByPersonalId(id)
                    .stream().map(asistenciaMapper::toDTO).toList();
        }
        return asistenciaRepository.findAll().stream().map(asistenciaMapper::toDTO).toList();

    }

    @Override
    public AsistenciaResponseDto registrarSalida(Long id) {
        Personal personal = personalRepository.findById(id).orElseThrow(()->
                new ErrorNegocio("El personal con ID "+ id +" no existe"));

        Asistencia asistencia = asistenciaRepository.findByFechaAndPersonalId
                                (LocalDate.now(),personal.getId()).orElseThrow(()->
                new ErrorNegocio("Para registrar la salida primero tienes que registrar la entrada"));

        asistencia.setHoraSalida(LocalTime.now().withNano(0));
        personal.setEstadoPersonal(EstadoPersonal.NO_DISPONIBLE);

        personalRepository.save(personal);
        return asistenciaMapper.toDTO(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaResponseDto registrarFalta(Long id) {
        Personal personal = personalRepository.findById(id).orElseThrow(()->
                new ErrorNegocio("El personal con ID "+ id +" no existe"));

        if(asistenciaRepository.existsByFechaAndPersonalId(LocalDate.now(),id)){
            throw new ErrorNegocio("No puedes registrar la asistencia de presencia 2 veces en un dia");
        }
        Asistencia asistencia = Asistencia.builder()
                .personal(personal).estadoAsistencia(EstadoAsistencia.FALTA).fecha(LocalDate.now())
                .horaEntrada(LocalTime.of(0,0)).horaSalida(LocalTime.of(0,0))

                .build();
        return asistenciaMapper.toDTO(asistenciaRepository.save(asistencia));
    }
}
