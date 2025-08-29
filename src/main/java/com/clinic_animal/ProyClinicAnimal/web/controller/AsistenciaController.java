package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.AsistenciaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
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



    @GetMapping
    public ResponseEntity<List<AsistenciaResponseDto>>listarById(@RequestParam(required = false) Long id, @RequestParam(required = false) EstadoAsistencia estado){
        return ResponseEntity.ok(asistenciaService.listar(estado,id));
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
