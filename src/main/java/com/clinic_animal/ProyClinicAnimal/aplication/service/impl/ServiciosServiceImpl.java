package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.ServiciosMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.ServiciosService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.domain.repository.AreaRepositry;
import com.clinic_animal.ProyClinicAnimal.domain.repository.ServiciosRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServiciosResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiciosServiceImpl implements ServiciosService {
    private final ServiciosMapper serviciosMapper;
    private final ServiciosRepository serviciosRepository;
    private final AreaRepositry areaRepositry;

    @Override
    public List<ServiciosResponseDto> lista() {
        return serviciosRepository.findAll().stream()
                .filter(Servicios::isActivo) // solo activos
                .map(serviciosMapper::toDTO)
                .toList();
    }

    @Override
    public ServiciosResponseDto obtenerById(Long id) {
        Servicios servicios = serviciosRepository.findById(id).
                orElseThrow(() -> new ErrorNegocio("No se encontro el servicio con id "+id));
        return serviciosMapper.toDTO(servicios);

    }

    @Override
    @Transactional
    public ServiciosResponseDto crear(ServiciosRequestDto requestDto) {
        Areas area = areaRepositry.findById(requestDto.getIdArea()).orElseThrow(()->
             new ErrorNegocio("No existe el codigo de area "+requestDto.getIdArea()));
        requestDto.setActivo(true);
        Servicios servicios = serviciosMapper.toEntity(requestDto,area);
         return serviciosMapper.toDTO(serviciosRepository.save(servicios));
    }

    @Override
    public ServiciosResponseDto deshabilitar(Long id, ServiciosUpdateEstadoDto serviciosUpdateEstadoDto) {
        Servicios s = serviciosRepository.findById(id)
                .orElseThrow(() -> new ErrorNegocio("No se encontró el servicio con id: " + id));

        s.setActivo(serviciosUpdateEstadoDto.isActivo());
        Servicios sActualizado= serviciosRepository.save(s);
        return serviciosMapper.toDTO(sActualizado);
    }

    @Override
    @Transactional
    public ServiciosResponseDto editar(Long id,ServiciosRequestDto requestDto ) {
        Servicios servicios = serviciosRepository.findById(id).orElseThrow(()->
                new ErrorNegocio(("Servicio con id "+id+"no encontrado")));

        servicios.setNombre(requestDto.getNombre());
        servicios.setDescripcion(requestDto.getDescripcion());
        servicios.setPrecio(requestDto.getPrecio());
        servicios.setDuracion(requestDto.getDuracion());
        return serviciosMapper.toDTO(serviciosRepository.save(servicios));
    }


}
