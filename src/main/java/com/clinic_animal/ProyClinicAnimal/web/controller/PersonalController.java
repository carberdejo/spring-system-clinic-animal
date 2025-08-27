package com.clinic_animal.ProyClinicAnimal.web.controller;


import com.clinic_animal.ProyClinicAnimal.aplication.service.PersonalService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalUpdateEstadoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.PersonalResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.PersonalUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Personal")
@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService personalService;
    @PostMapping
    public ResponseEntity<PersonalResponseDto> crear(@RequestBody PersonalRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personalService.crear(requestDto));
    }
    @GetMapping
    public ResponseEntity<List<PersonalResponseDto>> listar(){
        return ResponseEntity.ok(personalService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonalResponseDto> BuscarporId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(personalService.obtenerporId(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PersonalResponseDto> actualizarAreas(@PathVariable("id") Long id,@RequestBody PersonalUpdateDto requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(personalService.actualizar(requestDTO, id));
    }
    @GetMapping("/RolABuscar/{rolNombre}")
    public ResponseEntity<List<PersonalResponseDto>> listarRoles(@PathVariable String rolNombre){
        return ResponseEntity.ok(personalService.obtenerporRolNombre(rolNombre));
    }
    @GetMapping("/AreaABuscar/{nomArea}")
    public ResponseEntity<List<PersonalResponseDto>> listarArea(@PathVariable String nomArea){
        return ResponseEntity.ok(personalService.obtenerporNomArea(nomArea));
    }
    @PatchMapping("/CambiarEstado/{id}")
    public ResponseEntity<PersonalResponseDto> actualizarEstadoAreas(@PathVariable("id") Long id,@RequestBody PersonalUpdateEstadoDto updateEstadoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(personalService.actualizarEstado(updateEstadoDto, id));
    }
}
