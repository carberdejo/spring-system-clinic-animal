package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.HorarioMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.HorarioService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Horario;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoHorario;
import com.clinic_animal.ProyClinicAnimal.domain.repository.HorarioRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.RolRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HorarioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {
    private  final HorarioMapper horarioMapper;
    private final HorarioRepository horarioRepository;
    private final RolRepository rolRepository;

    private void validarHoras(LocalTime entrada,LocalTime salida){
        LocalTime horaEntrada =LocalTime.of(8,30);
        LocalTime horaSalida = LocalTime.of(18,0);
        if(entrada.isBefore(horaEntrada)){
            throw new ErrorNegocio("La hora de entrada no puede ser antes de las 08:30 AM");
        }
        if(salida.isAfter(horaSalida)){
            throw new ErrorNegocio("La hora de salida no puede ser despues de las 06:00 PM");
        }
        if(entrada.isAfter(salida)){
            throw new ErrorNegocio("Error al ingresar los horarios,la hora de entrada no puede ser posterior a la de salida");
        }
    }

    @Override
    public List<HorarioResponseDto> listar() {
        return horarioRepository.findAll().stream()
                .map(horarioMapper::toDTO).toList();
    }

    @Override
    public HorarioResponseDto obtenerById(Long id) {
        return horarioRepository.findById(id).map(horarioMapper::toDTO)
                .orElseThrow(()->new ErrorNegocio("Horario con ID "+id+" no encontrado"));
    }

    @Override
    public HorarioResponseDto crear(HorarioRequestDto requestDto) {
        Roles rol = rolRepository.findById(requestDto.getIdRol()).orElseThrow(()->
                new ErrorNegocio("Rol con ID"+requestDto.getIdRol()+" no encontrado"));

        validarHoras(requestDto.getHoraEntrada(),requestDto.getHoraSalida());

        Optional<Horario> horarioAnterior = Optional.ofNullable(horarioRepository.
                findAllByRolesRolCodigoAndEstadoHorario(requestDto.getIdRol(),EstadoHorario.DISPONIBLE));
        horarioAnterior.ifPresent(h-> {
            h.setEstadoHorario(EstadoHorario.DESHABILITADO);
            horarioRepository.save(h);
        });


        Horario horario = horarioMapper.toEntity(requestDto,rol);
        return horarioMapper.toDTO(horarioRepository.save(horario));

    }

    @Override
    public HorarioResponseDto UpdateHoras(Long id,HorarioUpdateRequestDto updateRequestDto) {
        Horario update = horarioRepository.findById(id)
                .orElseThrow(()->new ErrorNegocio("Horario con ID "+id+" no encontrado"));

        if(update.getEstadoHorario() == EstadoHorario.DESHABILITADO){
            throw  new ErrorNegocio("No puedes actualizar el horario si esta DESHABILITADO");
        }
        validarHoras(updateRequestDto.getHoraEntrada(),updateRequestDto.getHoraSalida());

        update.setHoraEntrada(updateRequestDto.getHoraEntrada());
        update.setHoraSalida(updateRequestDto.getHoraSalida());
        return horarioMapper.toDTO(horarioRepository.save(update));
    }

    @Override
    public void eliminar(Long id) {
        Horario delete = horarioRepository.findById(id)
                .orElseThrow(()->new ErrorNegocio("Horario con ID "+id+" no encontrado"));

        if(delete.getEstadoHorario()==EstadoHorario.DESHABILITADO){
            throw new ErrorNegocio("El horario ya esta deshabilitado");
        }

        delete.setEstadoHorario(EstadoHorario.DESHABILITADO);
        horarioRepository.save(delete);
    }
}
