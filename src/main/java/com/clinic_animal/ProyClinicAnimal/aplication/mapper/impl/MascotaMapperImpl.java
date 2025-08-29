package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.MascotaMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cliente;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.MascotaResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapperImpl implements MascotaMapper {

    @Override
    public Mascota toEntity(MascotaRequestDto dto) {
        if (dto == null) return null;
        Cliente cliente = Cliente.builder().id(dto.getIdCliente()).build();
        return Mascota.builder()
                .nombre(dto.getNombre())
                .raza(dto.getRaza())
                .especie(dto.getEspecie())
                .edad(dto.getEdad())
                .sexo(dto.getSexo())
                .cliente(cliente)
                .build();
    }

    @Override
    public void updateEntity(Mascota mascota, MascotaUpdateRequestDto dto) {
        if (dto == null) return;
        mascota.setNombre(dto.getNombre());
        mascota.setRaza(dto.getRaza());
        mascota.setEspecie(dto.getEspecie());
        mascota.setEdad(dto.getEdad());
        mascota.setSexo(dto.getSexo());
        if (dto.getIdCliente() != null) {
            mascota.setCliente(Cliente.builder().id(dto.getIdCliente()).build());
        }
    }

    @Override
    public MascotaResponseDto toResponse(Mascota mascota) {
        if (mascota == null) return null;
        return MascotaResponseDto.builder()
                .id(mascota.getId())
                .nombre(mascota.getNombre())
                .raza(mascota.getRaza())
                .especie(mascota.getEspecie())
                .edad(mascota.getEdad())
                .sexo(mascota.getSexo())
                .idCliente(mascota.getCliente() != null ? mascota.getCliente().getId() : null)
                .build();
    }
}
