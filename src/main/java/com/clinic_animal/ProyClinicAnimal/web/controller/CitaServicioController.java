package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaServicioService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaServicioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaServicioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citaservicio")
@RequiredArgsConstructor
public class CitaServicioController {
    private final CitaServicioService citaServicioService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<CitaServicioResponseDto>>listar(){
        return ResponseEntity.ok(citaServicioService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaServicioResponseDto>buscarById(@PathVariable Long id){
        return ResponseEntity.ok(citaServicioService.obtenerById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CitaServicioResponseDto>crear(@RequestBody CitaServicioRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(citaServicioService.crear(requestDto));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<CitaServicioResponseDto>editar(@PathVariable Long id,@RequestBody CitaServicioRequestDto requestDto){
        return ResponseEntity.ok(citaServicioService.editar(id,requestDto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        citaServicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
