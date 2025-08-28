package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaMaper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaStatusService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import com.clinic_animal.ProyClinicAnimal.domain.repository.CitaRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.PersonalRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CitaStatusServiceImpl implements CitaStatusService {
    private  final CitaRepository citaRepository;
    private final PersonalRepository personalRepository;

    private final CitaMaper citaMaper;

    private static final Set<String> ROLES_NO_VET = Set.of("admin","recepcionista");
    @Override
    public CitaResponseDto cambiarEstado(Long id,Long idCita, CitaUpdateDTO citaUpdateDTO) {
        Personal personal = personalRepository.findById(id).orElseThrow(()->
                new ErrorNegocio("El ID del personal no se ha encontrado"));

        String rol = personal.getRoles().getRolNombre().toLowerCase();
        if(!ROLES_NO_VET.contains(rol)){
            throw new ErrorNegocio("Solo tience acceso la recepcionista y el admin");
        }
        Cita cita = citaRepository.findById(idCita).orElseThrow(()->
                new ErrorNegocio("El ID de la cita no se ha encontrado"));

        if(cita.getEstado().validarCambiarEstado()){
            throw new ErrorNegocio("No puedes cambiar el estado de una cita que esta en progreso, cancelado o termiando");
        }

        cita.setEstado(citaUpdateDTO.getEstado());

        return citaMaper.toDto(citaRepository.save(cita));
    }

    @Override
    public CitaResponseDto citaEnProgreso(Long id,Long idCita) {
        Personal personal = personalRepository.findById(id).orElseThrow(()->
                new ErrorNegocio("El ID del personal no se ha encontrado"));

        String rol = personal.getRoles().getRolNombre().toLowerCase();
        if(ROLES_NO_VET.contains(rol)){
            throw new ErrorNegocio("Solo tienen acceso los veterinarios");
        }

        Cita cita = citaRepository.findById(idCita).orElseThrow(()->
                new ErrorNegocio("El ID de la cita no se ha encontrado"));

        if(!cita.getVeterinario().getId().equals( personal.getId())){
            throw new ErrorNegocio("El personal no esta agendado a esta cita");
        }

        if(personal.getEstadoPersonal() != EstadoPersonal.DISPONIBLE){
            throw new ErrorNegocio("El veterinario tiene que estar disponible");
        }

        if(cita.getEstado() != EstadoCita.EN_COLA){
            throw new ErrorNegocio("Solo pueden entrar en progreso las citas que estan en cola");
        }
        cita.setEstado(EstadoCita.EN_PROGRESO);
        personal.setEstadoPersonal(EstadoPersonal.OCUPADO);
        personalRepository.save(personal);
        return citaMaper.toDto(citaRepository.save(cita));
    }

    @Override
    public CitaResponseDto citaTerminada(Long id, Long idCita) {
        Personal personal = personalRepository.findById(id).orElseThrow(()->
                new ErrorNegocio("El ID del personal no se ha encontrado"));

        String rol = personal.getRoles().getRolNombre().toLowerCase();
        if(ROLES_NO_VET.contains(rol)){
            throw new ErrorNegocio("Solo tienen acceso los veterinarios");
        }

        Cita cita = citaRepository.findById(idCita).orElseThrow(()->
                new ErrorNegocio("El ID de la cita no se ha encontrado"));

        if(!cita.getVeterinario().getId().equals( personal.getId())){
            throw new ErrorNegocio("El personal no esta agendado a esta cita");
        }

        if(cita.getEstado() != EstadoCita.EN_PROGRESO){
            throw new ErrorNegocio("Solo pueden finalizar las citas que estan en progreso");
        }

        cita.setEstado(EstadoCita.TERMINADA);
        personal.setEstadoPersonal(EstadoPersonal.DISPONIBLE);
        personalRepository.save(personal);
        return citaMaper.toDto(citaRepository.save(cita));
    }

    @Override
    public CitaResponseDto citaReProgramada(Long idCita, Long id,LocalDateTime fecha) {
        Personal personal = personalRepository.findById(id).orElseThrow(()->
                new ErrorNegocio("El ID del personal no se ha encontrado"));

        String rol = personal.getRoles().getRolNombre().toLowerCase();
        if(!ROLES_NO_VET.contains(rol)){
            throw new ErrorNegocio("Solo tience acceso la recepcionista y el admin");
        }
        Cita cita = citaRepository.findById(idCita).orElseThrow(()->
                new ErrorNegocio("El ID de la cita no se ha encontrado"));

        if(cita.getEstado().validarCambiarEstado()){
            throw new ErrorNegocio("No puedes reprogramar el estado de esta cita");
        }
        cita.setEstado(EstadoCita.PROGRAMADA);
        cita.setFechaHora(fecha);

        return citaMaper.toDto(citaRepository.save(cita));
    }

    @Override
    public List<CitaResponseDto> listaCitasEnColaByVet(Long id) {
        return List.of();
    }
}
