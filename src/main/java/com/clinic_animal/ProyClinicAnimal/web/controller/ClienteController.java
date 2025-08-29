package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.ClienteService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ClienteUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ClienteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> crearCliente(@RequestBody ClienteRequestDto dto) {
        return ResponseEntity.ok(clienteService.crearCliente(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> actualizarCliente(@PathVariable Long id,
                                                                @RequestBody ClienteUpdateRequestDto dto) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }
}
