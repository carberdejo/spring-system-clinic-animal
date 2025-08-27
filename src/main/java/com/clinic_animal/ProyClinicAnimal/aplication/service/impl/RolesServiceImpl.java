package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.RolesMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.RolesService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.domain.repository.RolRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RolesRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RolesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RolesMapper rolesMap;
    private final RolRepository rolRep;
    @Override
    public RolesResponseDto crear(RolesRequestDto rolesRequestDto) {
        if(rolRep.existsByRolNombre(rolesRequestDto.getRolNombre()))
            throw new ErrorNegocio("Esa Area ya existe");
        Roles roles = rolesMap.toEntity(rolesRequestDto);
        return rolesMap.toDto(rolRep.save(roles));
    }

    @Override
    public List<RolesResponseDto> listar() {
        return rolRep.findAll()
                .stream()
                .map(rolesMap::toDto)
                .toList();
    }

    @Override
    public RolesResponseDto obtenerporId(Long id) {
        Roles rolBuscado = rolRep.findById(id).orElseThrow(()
                -> new ErrorNegocio("Esa area no existe"));

        return rolesMap.toDto(rolBuscado);
    }

    @Override
    public RolesResponseDto actualizar(RolesRequestDto rolesRequestDto, Long id) {
        Roles rolBuscado = rolRep.findById(id).orElseThrow(()
                -> new ErrorNegocio("Esa area no existe"));
        rolBuscado.setRolNombre(rolesRequestDto.getRolNombre());
        rolBuscado.setDescripcion(rolesRequestDto.getDescripcion());
        Roles rolActualizado = rolRep.save(rolBuscado);
        return rolesMap.toDto(rolActualizado);

    }
}
