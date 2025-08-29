package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    ClienteResponseDto crearCliente(ClienteRequestDto dto);
    ClienteResponseDto actualizarCliente(Long id, ClienteUpdateRequestDto dto);
    void eliminarCliente(Long id);
    ClienteResponseDto obtenerClientePorId(Long id);
    List<ClienteResponseDto> listarClientes();
}
