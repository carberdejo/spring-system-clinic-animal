package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.ClienteMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.ClienteService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cliente;
import com.clinic_animal.ProyClinicAnimal.domain.repository.ClienteRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ClienteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteResponseDto crearCliente(ClienteRequestDto dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toResponse(guardado);
    }

    @Override
    public ClienteResponseDto actualizarCliente(Long id, ClienteUpdateRequestDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
        clienteMapper.updateEntity(cliente, dto);
        Cliente actualizado = clienteRepository.save(cliente);
        return clienteMapper.toResponse(actualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteResponseDto obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
    }

    @Override
    public List<ClienteResponseDto> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .collect(Collectors.toList());
    }
}
