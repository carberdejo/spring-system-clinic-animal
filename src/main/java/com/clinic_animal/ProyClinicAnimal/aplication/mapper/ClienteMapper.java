package com.clinic_animal.ProyClinicAnimal.aplication.mapper;

import com.clinic_animal.ProyClinicAnimal.domain.model.Cliente;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ClienteResponseDto;

public interface ClienteMapper {
    Cliente toEntity(ClienteRequestDto dto);
    void updateEntity(Cliente cliente, ClienteUpdateRequestDto dto);
    ClienteResponseDto toResponse(Cliente cliente);
}
