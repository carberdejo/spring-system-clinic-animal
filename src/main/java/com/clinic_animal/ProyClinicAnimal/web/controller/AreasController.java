package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.AreasService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Area")
@RequiredArgsConstructor
public class AreasController {
    private final AreasService areasService;
    @PostMapping
    public ResponseEntity<AreasResponseDto> crear(@RequestBody AreasRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(areasService.crear(requestDto));
    }
    @GetMapping
    public ResponseEntity<List<AreasResponseDto>> listar(){
        return ResponseEntity.ok(areasService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AreasResponseDto> BuscarporId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(areasService.obtenerporId(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AreasResponseDto> actualizarAreas(@PathVariable("id") Long id,@RequestBody AreasRequestDto requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(areasService.actualizar(requestDTO , id));
    }
}
