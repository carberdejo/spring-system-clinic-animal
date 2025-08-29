package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaMaper;
import com.clinic_animal.ProyClinicAnimal.domain.model.*;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaServicioResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CitaMapperImpl implements CitaMaper {
    @Override
    public Cita toEntity(CitaRequestDto dto, Mascota mascota, Cliente cliente, Personal recep, Personal vet, Areas areas) {
        return Cita.builder()
                .fechaHora(LocalDateTime.now())
                .estado(dto.getEstadoCita())
                .mascota(mascota)
                .cliente(cliente)
                .recepcionista(recep)
                .veterinario(vet)
                .area(areas)
                .build();}

    @Override
    public CitaResponseDto toDto(Cita entity) {
        return CitaResponseDto.builder()
                .id(entity.getId())
                .fechaHora(entity.getFechaHora())
                .estado(entity.getEstado())
                .idArea(entity.getArea().getCodigoArea())
                .nomArea(entity.getArea().getNomArea())
                .mascotaId(entity.getMascota().getId())
                .nombreMascota(entity.getMascota().getNombre())
                .idCliente(entity.getCliente().getId())
                .nomCliente(entity.getCliente().getNombre())
                .idRecepcionista(entity.getRecepcionista().getId())
                .nomRecepcionista(entity.getRecepcionista().getNombrePersonal())
                .idVeterinario(entity.getVeterinario() != null ? entity.getVeterinario().getId() : null)
                .nomVeterinario(entity.getVeterinario() != null ? entity.getVeterinario().getNombrePersonal() : null)
                .servicios(
                        entity.getCitaServicios().stream()
                                .map(cs -> CitaServicioResponseDto.builder()
                                        .id(cs.getId())
                                        .cantidad(cs.getCantidad())
                                        .precioBase(cs.getPrecioBase())
                                        .subTotal(cs.getSubTotal())
                                        .idCita(cs.getCita().getId())
                                        .idServicio(cs.getServicios().getId())
                                        .nomServicio(cs.getServicios().getNombre())
                                        .build()
                                ).toList()
                )
                .build();
    }
}
