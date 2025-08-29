package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.HorarioService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.HorarioUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HorarioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<HorarioResponseDto>>listar(){
        return ResponseEntity.ok(horarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioResponseDto>buscarById(@PathVariable Long id){
        return ResponseEntity.ok(horarioService.obtenerById(id));
    }

    @PostMapping
    public ResponseEntity<HorarioResponseDto>crear(@RequestBody HorarioRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.crear(requestDto));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<HorarioResponseDto>updateHoras(@PathVariable Long id, @RequestBody HorarioUpdateRequestDto updateRequestDto){
        return ResponseEntity.ok(horarioService.UpdateHoras(id,updateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
