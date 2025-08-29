package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.PersonalMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.*;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.PersonalResponseDto;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder

public class PersonalMapperImpl implements PersonalMapper {
    @Override
    public Personal toEntity(PersonalRequestDto dto, Roles roles, Areas areas) {
        return Personal.builder()
                .nombrePersonal(dto.getNombrePersonal())
                .apellidoPersonal(dto.getApellidoPersonal())
                .contraseña(dto.getContraseña())
                .edad(dto.getEdad())
                .dni(dto.getDni())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .estadoPersonal(EstadoPersonal.DISPONIBLE)
                .roles(roles)
                .areas(areas)
                .build();
    }

    @Override
    public PersonalResponseDto toDto(Personal entity) {
        return PersonalResponseDto.builder()
                .id(entity.getId())
                .nombrePersonal(entity.getNombrePersonal())
                .apellidoPersonal(entity.getApellidoPersonal())
                .contraseña(entity.getContraseña())
                .dni(entity.getDni())
                .edad(entity.getEdad())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .estadoPersonal(entity.getEstadoPersonal())
                .codigoArea(entity.getAreas().getCodigoArea())
                .nomArea(entity.getAreas().getNomArea())
                .rolCodigo(entity.getRoles().getRolCodigo())
                .nombreRol(entity.getRoles().getRolNombre())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }

    @Override
    public void PersonalUpdateDto(PersonalUpdateDto dto, Personal entity) {

        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getTelefono() != null) {
            entity.setTelefono(dto.getTelefono());
        }
    }

    @Override
    public void PersonalUpdateEstadoDto(PersonalUpdateEstadoDto dto, Personal entity) {
        if (dto.getEstadoPersonal() != null) {
            entity.setEstadoPersonal(dto.getEstadoPersonal());
        }
    }

    @Override
    public void PersonalUpdateRolesDto(PersonalUpdateRolesDto dto, Personal entity, Roles roles, Areas areas) {
        if (dto.getRolCodigo() != null) {
            entity.setRoles(roles);
        }
        if (dto.getCodigoArea() != null) {
            entity.setAreas(areas);
        }
        if(dto.getContraseña()!= null){
            entity.setContraseña(dto.getContraseña());
        }
    }

    @Override
    public void PersonalRemoverDescansoDto(PersonalRemoverDescansoDto personalRemoverDescansoDto, Personal entity) {
        entity.setEstadoPersonal(EstadoPersonal.DISPONIBLE);
    }
}
