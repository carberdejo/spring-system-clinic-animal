package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.ServiciosService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.ServiciosUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.ServiciosResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServiciosController {
    private final ServiciosService serviciosService;

    @GetMapping
    public ResponseEntity<List<ServiciosResponseDto>> listar(){
        return ResponseEntity.ok(serviciosService.lista());
    }
    @PostMapping
    public ResponseEntity<ServiciosResponseDto>crear(@RequestBody ServiciosRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviciosService.crear(requestDto));
    }
    @PatchMapping("{id}")
    public ResponseEntity<ServiciosResponseDto>editar(@PathVariable("id") Long id,@RequestBody ServiciosRequestDto requestDto){
        return ResponseEntity.ok(serviciosService.editar(id,requestDto));
    }
    @PatchMapping("/Estado/{id}")
    public ResponseEntity<ServiciosResponseDto>deshabilitar(@PathVariable("id") Long id,@RequestBody ServiciosUpdateEstadoDto serviciosUpdateEstadoDto){
        return ResponseEntity.ok(serviciosService.deshabilitar(id,serviciosUpdateEstadoDto));
    }


}
