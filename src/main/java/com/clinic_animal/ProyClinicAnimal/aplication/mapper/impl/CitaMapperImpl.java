package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaMaper;
import com.clinic_animal.ProyClinicAnimal.domain.model.*;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
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
                .mascotaId(entity.getMascota().getId())
                .nombreMascota(entity.getMascota().getNombre())
                .idCliente(entity.getCliente().getId())
                .nomCliente(entity.getCliente().getNombre())
                .idRecepcionista(entity.getRecepcionista().getId())
                .nomRecepcionista(entity.getRecepcionista().getNombrePersonal())
                .idVeterinario(entity.getVeterinario().getId())
                .nomVeterinario(entity.getVeterinario().getNombrePersonal())
                .idArea(entity.getArea().getCodigoArea())
                .nomArea(entity.getArea().getNomArea())
                .nombreMascota(entity.getMascota().getNombre())
                .tieneReceta(entity.getReceta() != null)
                .build();}
}
