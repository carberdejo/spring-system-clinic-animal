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
import com.clinic_animal.ProyClinicAnimal.web.dto.request.*;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.PersonalResponseDto;
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
        if (personalRep.existsByEmail(personalRequestDto.getEmail()))
            throw new ErrorNegocio("Ese email ya esta registrado");
        Roles rol = rolesRep.findByRolCodigo(personalRequestDto.getRolCodigo());
        if(!rol.isAccesoWeb()) {
            if (personalRequestDto.getContraseña() != null && !personalRequestDto.getContraseña().isEmpty()) {
                throw new ErrorNegocio("Este rol no puede tener contraseña");
            }
        }else{
            if (personalRequestDto.getContraseña().length() < 7) {
                throw new ErrorNegocio("Contraseña muy pequeña");
            }
            if (!personalRequestDto.getContraseña().matches(".*\\d.*")) {
                throw new ErrorNegocio("Tienes que añadir al menos un numero a tu contraseña por seguridad");
            }
        }
        if (!areasRep.existsByCodigoArea(personalRequestDto.getCodigoArea())) {
            throw new ErrorNegocio("Esta Área no existe");
        }
        if (!rolesRep.existsByRolCodigo(personalRequestDto.getRolCodigo())) {
            throw new ErrorNegocio("Este rol no existe");
        }
        if(!personalRequestDto.getEmail().endsWith("@gmail.com"))
            throw new ErrorNegocio("Aprende a escribir un correo");
        if(personalRequestDto.getEdad() <17){
            throw new ErrorNegocio("Eres muy pequeño para trabajar :(");
        }
        if(personalRequestDto.getDni().length()!=8){
            throw new ErrorNegocio("Tu dni debe tener 8 digitos");
        }
        if(personalRequestDto.getTelefono().length() != 9){
            throw new ErrorNegocio("Todo numero de telefono debe tener 9 digitos");
        }
        Roles roles = rolesRep.findByRolCodigo(personalRequestDto.getRolCodigo());
        Areas area = areasRep.findByCodigoArea(personalRequestDto.getCodigoArea());
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
    public List<PersonalResponseDto> obtenerporRolCodigo(Long rolCodigo) {
        List<Personal> personales = personalRep.findAllByRoles_RolCodigoAndEstadoPersonalNot(rolCodigo, EstadoPersonal.NO_DISPONIBLE);
        return personales.stream()
                .map(personalMap::toDto)
                .toList()
                ;

    }

    @Override
    public List<PersonalResponseDto> obtenerporCodigoArea(Long codigoArea) {
        List<Personal> personales = personalRep.findAllByAreas_CodigoAreaAndEstadoPersonalNot(codigoArea, EstadoPersonal.NO_DISPONIBLE);
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

        if (personalRep.existsByEmail(personalUpdateDto.getEmail()))
            throw new ErrorNegocio("Ese email ya esta registrado");
        // Buscar Área por nombre
        Areas area = areasRep.findByCodigoArea(personalUpdateDto.getCodigoArea());
        if (area == null) {
            throw new ErrorNegocio("El área de codigo" + personalUpdateDto.getCodigoArea() + "' no existe");
        }
        // Buscar Rol por nombre
        Roles rol = rolesRep.findByRolCodigo(personalUpdateDto.getRolCodigo());
        if (rol == null) {
            throw new ErrorNegocio("El rol de codigo" + personalUpdateDto.getCodigoArea() + "' no existe");
        }
        // Actualizar campos
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
               EstadoPersonal pEstadoAntiguo = personal.getEstadoPersonal();
        EstadoPersonal pEstadoNuevo = personalUpdateEstadoDto.getEstadoPersonal();
               if(pEstadoAntiguo == EstadoPersonal.OCUPADO && pEstadoNuevo == EstadoPersonal.DESCANSO){
                   throw new ErrorNegocio("No le puedes dar descanso a alguien que este ocupado");
               }
        personal.setEstadoPersonal(personalUpdateEstadoDto.getEstadoPersonal());
        Personal personalActu = personalRep.save(personal);
        return personalMap.toDto(personalActu);
    }

    @Override
    public PersonalResponseDto actualizarRoles(PersonalUpdateRolesDto personalUpdateRolesDto, Long id) {
        Personal personal = personalRep.findById(id)
                .orElseThrow(() -> new ErrorNegocio("No se encontró el trabajador con id: " + id));

        // Obtener área
        Areas area = areasRep.findByCodigoArea(personalUpdateRolesDto.getCodigoArea());
        if (area == null) {
            throw new ErrorNegocio("El área de código " + personalUpdateRolesDto.getCodigoArea() + " no existe");
        }

        // Obtener rol nuevo
        Roles rolNuevo = rolesRep.findByRolCodigo(personalUpdateRolesDto.getRolCodigo());
        if (rolNuevo == null) {
            throw new ErrorNegocio("El rol de código " + personalUpdateRolesDto.getRolCodigo() + " no existe");
        }

        // Obtener rol anterior
        Roles rolAnterior = personal.getRoles();

        // Manejo de contraseña según accesoWeb
        if (!rolAnterior.isAccesoWeb() && rolNuevo.isAccesoWeb()) {
            // Cambia de rol sin acceso → con acceso → exigir contraseña
            if (personalUpdateRolesDto.getContraseña() == null || personalUpdateRolesDto.getContraseña().isEmpty()) {
                throw new ErrorNegocio("Debes ingresar una contraseña al cambiar a un rol con acceso web");
            }
            // Validación de contraseña
            if (personalUpdateRolesDto.getContraseña().length() < 7) {
                throw new ErrorNegocio("Contraseña muy pequeña");
            }
            if (!personalUpdateRolesDto.getContraseña().matches(".*\\d.*")) {
                throw new ErrorNegocio("La contraseña debe contener al menos un número");
            }
            personal.setContraseña(personalUpdateRolesDto.getContraseña());
        } else if (!rolNuevo.isAccesoWeb()) {
            // Si el rol nuevo no tiene acceso web → borrar contraseña
            personal.setContraseña(null);
        }
        else if(rolNuevo.isAccesoWeb() && rolAnterior.isAccesoWeb() && personalUpdateRolesDto.getContraseña() != null /*&& !personal.getRoles().getRolNombre().equals("admin")*/){
            throw new ErrorNegocio("Solo el Administrador puede cambiar contraseñas");
        }

        // Actualizar campos normales
        personal.setAreas(area);
        personal.setRoles(rolNuevo);

        Personal personalActu = personalRep.save(personal);
        return personalMap.toDto(personalActu);
    }

    @Override
    public PersonalResponseDto finalizarDescanso(PersonalRemoverDescansoDto personalRemoverDescansoDto, Long id) {
        Personal personal = personalRep.findById(id)
                .orElseThrow(() -> new ErrorNegocio("No se encontró el trabajador con id: " + id));
    if(personal.getEstadoPersonal()==EstadoPersonal.DESCANSO) {
        personal.setEstadoPersonal(EstadoPersonal.DISPONIBLE);
    }
    else{
        throw new ErrorNegocio("Solo puedes remover el descanso de alguien que este descansando");
    }
        Personal personalActu = personalRep.save(personal);
        return personalMap.toDto(personalActu);
    }


}
