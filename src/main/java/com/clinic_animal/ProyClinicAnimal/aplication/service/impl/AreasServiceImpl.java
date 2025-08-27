package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.AreasMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.AreasService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.repository.AreaRepositry;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class AreasServiceImpl implements AreasService {
    private final AreasMapper areasMap;
    private final AreaRepositry areasRep;

    @Override
    public AreasResponseDto crear(AreasRequestDto areasRequestDTO) {
        if(areasRep.existsByNomArea(areasRequestDTO.getNomArea()))
             throw new ErrorNegocio("Esa Area ya existe");
        Areas areas = areasMap.toEntity(areasRequestDTO);
        return areasMap.toDto(areasRep.save(areas));

    }

    @Override
    public List<AreasResponseDto> listar() {
        return areasRep.findAll()
                .stream()
                .map(areasMap::toDto)
                .toList();

    }

    @Override
    public AreasResponseDto obtenerporId(Long id) {
        Areas areaBuscada = areasRep.findById(id).orElseThrow(()
                -> new ErrorNegocio("Esa area no existe"));

        return areasMap.toDto(areaBuscada);
    }

    @Override
    public AreasResponseDto actualizar(AreasRequestDto areasRequestDTO, Long id) {
        Areas areaBuscada = areasRep.findById(id).orElseThrow(()
                -> new ErrorNegocio("Esa area no existe"));
        areaBuscada.setNomArea(areasRequestDTO.getNomArea());
        areaBuscada.setDescripcion(areasRequestDTO.getDescripcion());
        Areas areaActualizada= areasRep.save(areaBuscada);
        return areasMap.toDto(areaActualizada);

    }
}
