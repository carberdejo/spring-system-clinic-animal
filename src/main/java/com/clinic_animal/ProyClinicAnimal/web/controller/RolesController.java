package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.RolesService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.AreasRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RolesRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.AreasResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RolesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Roles")
@RequiredArgsConstructor
public class RolesController {
    private final RolesService rolesService;

    @PostMapping
    public ResponseEntity<RolesResponseDto> crear(@RequestBody RolesRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesService.crear(requestDto));
    }
    @GetMapping
    public ResponseEntity<List<RolesResponseDto>> listar(){
        return ResponseEntity.ok(rolesService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RolesResponseDto> BuscarporId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(rolesService.obtenerporId(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<RolesResponseDto> actualizarAreas(@PathVariable("id") Long id,@RequestBody RolesRequestDto requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(rolesService.actualizar(requestDTO , id));
    }
}
