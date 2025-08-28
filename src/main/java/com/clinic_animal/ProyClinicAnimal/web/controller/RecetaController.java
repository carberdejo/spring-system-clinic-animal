package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.RecetaService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaUpdateDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {
    private final RecetaService recetaService;


    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<RecetaResponseDto>> listar() {
        return ResponseEntity.ok(recetaService.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }


    @PostMapping
    public ResponseEntity<RecetaResponseDto> crear(@RequestBody RecetaRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recetaService.crear(requestDto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<RecetaResponseDto> actualizar(@PathVariable Long id,
                                                        @RequestBody RecetaUpdateDto updateDTO) {
        return ResponseEntity.ok(recetaService.actualizar(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cant/{cantidad}")
    public ResponseEntity<List<RecetaResponseDto>> buscarPorCantidad(@PathVariable Integer cantidad) {
        return ResponseEntity.ok(recetaService.buscarPorCantidad(cantidad));
    }

}
