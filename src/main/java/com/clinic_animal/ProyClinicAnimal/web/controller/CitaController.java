package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cita")
@RequiredArgsConstructor
public class CitaController {
    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponseDto>> listar(@RequestParam(required = false) EstadoCita estado)
    {
        return ResponseEntity.ok(citaService.listar(estado));
    }

    @PostMapping
    public ResponseEntity<CitaResponseDto> crear(@RequestBody CitaRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citaService.crear(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerPorId(id));
    }

    @GetMapping("/mascota/{id}")
    public ResponseEntity<List<CitaResponseDto>> listarPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(citaService.listarPorMascota(mascotaId));
    }
}
