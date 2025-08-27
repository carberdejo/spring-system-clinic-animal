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
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {
    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponseDto>> listar() {
        return ResponseEntity.ok(citaService.listar());
    }

    @PostMapping
    public ResponseEntity<CitaResponseDto> crear(@RequestBody CitaRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citaService.crear(requestDto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<CitaResponseDto> actualizarEstado(@PathVariable Long id,
                                                            @RequestBody CitaUpdateDTO requestDto) {
        return ResponseEntity.ok(citaService.actualizarEstado(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerPorId(id));
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<CitaResponseDto>> listarPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(citaService.listarPorMascota(mascotaId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaResponseDto>> listarPorEstado(@PathVariable EstadoCita estado) {
        return ResponseEntity.ok(citaService.listarPorEstado(estado));
    }
}
