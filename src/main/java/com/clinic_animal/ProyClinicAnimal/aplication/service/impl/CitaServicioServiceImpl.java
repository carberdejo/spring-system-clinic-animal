package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaServicioMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaServicioService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.CitaServicio;
import com.clinic_animal.ProyClinicAnimal.domain.model.Servicios;
import com.clinic_animal.ProyClinicAnimal.domain.repository.CitaRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.CitaServicioRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.ServiciosRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaServicioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaServicioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServicioServiceImpl implements CitaServicioService {
    private final CitaServicioRepository citaServicioRepository;
    private final CitaRepository citaRepository;
    private final ServiciosRepository serviciosRepository;
    private final CitaServicioMapper citaServicioMapper;

    @Override
    public List<CitaServicioResponseDto> listar() {
        return citaServicioRepository.findAll().stream()
                .map(citaServicioMapper::toDTO).toList();
    }

    @Override
    public CitaServicioResponseDto crear(CitaServicioRequestDto requestDto) {
        Cita cita = citaRepository.findById(requestDto.getIdCita()).orElseThrow(()->
                new ErrorNegocio("No se encontro del id de cita "+requestDto.getIdCita()));
        Servicios servicios = serviciosRepository.findById(requestDto.getIdServicio())
                .orElseThrow(()->new ErrorNegocio("No se encontro el did de servicios "+requestDto.getIdServicio()));
        if (!servicios.getArea().getCodigoArea().equals(cita.getArea().getCodigoArea())) {
            throw new ErrorNegocio("El servicio seleccionado no corresponde al área de la cita.");
        }
        CitaServicio citaServicio = citaServicioMapper.toEntity(requestDto,cita,servicios);
        Servicios serv = serviciosRepository.findById(requestDto.getIdServicio())
                .orElseThrow(() -> new ErrorNegocio("servicio con ID " + requestDto.getIdServicio() + " no encontrado"));
        return citaServicioMapper.toDTO(citaServicioRepository.save(citaServicio));

    }

    @Override
    public CitaServicioResponseDto editar(Long id,CitaServicioRequestDto requestDto) {
        CitaServicio citaServicio = citaServicioRepository.findById(id)
                .orElseThrow(()->new ErrorNegocio("citaServicio con id "+id+" no encontrado"));

        citaServicio.setCantidad(requestDto.getCantidad());
        citaServicio.setPrecioBase(requestDto.getPrecioBase());
        citaServicio.setSubTotal(requestDto.getCantidad() * requestDto.getPrecioBase());
        return citaServicioMapper.toDTO(citaServicioRepository.save(citaServicio));
    }

    @Override
    public CitaServicioResponseDto obtenerById(Long id) {
        CitaServicio citaServicio = citaServicioRepository.findById(id).orElseThrow(()->
                new ErrorNegocio(("citaServicio con id "+id+"no encontrado")));
        return citaServicioMapper.toDTO(citaServicio);
    }

    @Override
    public void eliminar(Long id) {
        CitaServicio citaServicio = citaServicioRepository.findById(id).orElseThrow(()->
                new ErrorNegocio(("citaServicio con id "+id+"no encontrado")));
        citaServicioRepository.delete(citaServicio);
    }
}
