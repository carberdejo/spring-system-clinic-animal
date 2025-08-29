package com.clinic_animal.ProyClinicAnimal.aplication.mapper.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.ClienteMapper;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cliente;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ClienteResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteRequestDto dto) {
        if (dto == null) return null;
        return Cliente.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public void updateEntity(Cliente cliente, ClienteUpdateRequestDto dto) {
        if (dto == null) return;
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setEmail(dto.getEmail());
    }

    @Override
    public ClienteResponseDto toResponse(Cliente cliente) {
        if (cliente == null) return null;
        return ClienteResponseDto.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .dni(cliente.getDni())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .email(cliente.getEmail())
                .build();
    }
}
