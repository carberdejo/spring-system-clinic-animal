package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.MascotaService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.MascotaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<MascotaResponseDto> crearMascota(@RequestBody MascotaRequestDto dto) {
        return ResponseEntity.ok(mascotaService.crearMascota(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> actualizarMascota(@PathVariable Long id,
                                                                @RequestBody MascotaUpdateRequestDto dto) {
        return ResponseEntity.ok(mascotaService.actualizarMascota(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> obtenerMascota(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.obtenerMascotaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponseDto>> listarMascotas() {
        return ResponseEntity.ok(mascotaService.listarMascotas());
    }
}
