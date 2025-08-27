package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.PersonalMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.PersonalService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import com.clinic_animal.ProyClinicAnimal.domain.repository.AreaRepositry;
import com.clinic_animal.ProyClinicAnimal.domain.repository.PersonalRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.RolRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.PersonalResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PersonalServiceImpl implements PersonalService {
    private final PersonalMapper personalMap;
    private final PersonalRepository personalRep;
    private final AreaRepositry areasRep;
    private final RolRepository rolesRep;

    @Override
    public PersonalResponseDto crear(PersonalRequestDto personalRequestDto) {

        if (personalRep.existsByDni(personalRequestDto.getDni()))
            throw new ErrorNegocio("Ese trabajador ya existe");
        if (!areasRep.existsByNomArea(personalRequestDto.getNomArea())) {
            throw new ErrorNegocio("Esta Área no existe");
        }
        if (!rolesRep.existsByRolNombre(personalRequestDto.getNombreRol())) {
            throw new ErrorNegocio("Este rol no existe");
        }
        if(personalRequestDto.getEdad() <17){
            throw new ErrorNegocio("Eres muy pequeño para trabajar :(");
        }
        if(personalRequestDto.getDni().length()!=8){
            throw new ErrorNegocio("Tu dni debe tener 8 digitos");
        }
        if(personalRequestDto.getTelefono().length() != 9){
            throw new ErrorNegocio("Todo numero de telefono debe tener 9 digitos");
        }
        Roles roles = rolesRep.findByRolNombre(personalRequestDto.getNombreRol());
        Areas area = areasRep.findByNomArea(personalRequestDto.getNomArea());
        Personal personal = personalMap.toEntity(personalRequestDto,roles,area);
        personal.setFechaRegistro(LocalDate.now());
        personal.setEstadoPersonal(EstadoPersonal.DISPONIBLE);
        Personal personalGuardado = personalRep.save(personal);
        return personalMap.toDto(personalGuardado);
    }

    @Override
    public List<PersonalResponseDto> listar() {

        return personalRep.findAllByEstadoPersonalNot(EstadoPersonal.NO_DISPONIBLE)
                .stream()
                .map(personalMap::toDto)
                .toList();

    }

    @Override
    public PersonalResponseDto obtenerporId(Long id) {

        Personal personal = personalRep.findById(id)
                .orElseThrow(() -> new ErrorNegocio("No se encontró el trabajador con id: " + id));
        if(personal.getEstadoPersonal().equals(EstadoPersonal.NO_DISPONIBLE)){
            throw new ErrorNegocio("Este trabajador ha sido despedido");
        }
        return personalMap.toDto(personal);

    }

    @Override
    public List<PersonalResponseDto> obtenerporRolNombre(String rolNombre) {
        List<Personal> personales = personalRep.findAllByRoles_RolNombreAndEstadoPersonalNot(rolNombre, EstadoPersonal.NO_DISPONIBLE);
        return personales.stream()
                .map(personalMap::toDto)
                .toList()
                ;

    }

    @Override
    public List<PersonalResponseDto> obtenerporNomArea(String nomArea) {
        List<Personal> personales = personalRep.findAllByAreas_NomAreaAndEstadoPersonalNot(nomArea, EstadoPersonal.NO_DISPONIBLE);
        return personales.stream()
                .map(personalMap::toDto)
                .toList()
                ;

    }

    @Override
    public PersonalResponseDto actualizar(PersonalUpdateDto personalUpdateDto, Long id) {
        Personal personal = personalRep.findById(id)
                .orElseThrow(() -> new ErrorNegocio("No se encontró el trabajador con id: " + id));

        // Validación de teléfono
        if (personalUpdateDto.getTelefono() == null || personalUpdateDto.getTelefono().length() != 9) {
            throw new ErrorNegocio("Todo número de teléfono debe tener 9 dígitos");
        }

        // Buscar Área por nombre
        Areas area = areasRep.findByNomArea(personalUpdateDto.getNomArea());
        if (area == null) {
            throw new ErrorNegocio("El área '" + personalUpdateDto.getNomArea() + "' no existe");
        }
        // Buscar Rol por nombre
        Roles rol = rolesRep.findByRolNombre(personalUpdateDto.getNombreRol());
        if (rol == null) {
            throw new ErrorNegocio("El área '" + personalUpdateDto.getNomArea() + "' no existe");
        }
        // Actualizar campos
        personal.setAreas(area);
        personal.setRoles(rol);
        personal.setEmail(personalUpdateDto.getEmail());
        personal.setTelefono(personalUpdateDto.getTelefono());

        // Guardar y devolver DTO
        Personal personalActu = personalRep.save(personal);
        return personalMap.toDto(personalActu);
    }


    @Override
    public PersonalResponseDto actualizarEstado(PersonalUpdateEstadoDto personalUpdateEstadoDto, Long id) {
        Personal personal = personalRep.findById(id)
                .orElseThrow(() -> new ErrorNegocio("No se encontró el trabajador con id: " + id));
               if(personal.getEstadoPersonal().equals(personalUpdateEstadoDto.getEstadoPersonal())){
               throw new ErrorNegocio("Este trabajador ya tiene ese estado");
       }
        personal.setEstadoPersonal(personalUpdateEstadoDto.getEstadoPersonal());
        Personal personalActu = personalRep.save(personal);
        return personalMap.toDto(personalActu);
    }

}
