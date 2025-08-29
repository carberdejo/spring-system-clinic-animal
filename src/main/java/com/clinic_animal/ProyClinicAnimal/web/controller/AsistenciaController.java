package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.AsistenciaService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AsistenciaSalidaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AsistenciaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {
    private final AsistenciaService asistenciaService;



    @GetMapping("/{id}")
    public ResponseEntity<List<AsistenciaResponseDto>>listarById(@PathVariable Long id){
        return ResponseEntity.ok(asistenciaService.listarById(id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaResponseDto>registrarEntrada(@RequestBody AsistenciaRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaService.registrarEntrada(requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AsistenciaResponseDto>registrarSalida(@PathVariable Long id){
        return ResponseEntity.ok(asistenciaService.registrarSalida(id));
    }

}
