package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaStatusService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citastatus")
@RequiredArgsConstructor
public class CitaStatusController {

    private final CitaStatusService citaStatusService;

    @GetMapping("/encola/{id}")
    public ResponseEntity<List<CitaResponseDto>>listarByVet(@PathVariable Long id){
        return ResponseEntity.ok(citaStatusService.listaCitasEnColaByVet(id));
    }

//    @PutMapping("estadoCambiar/{id}")
//    public ResponseEntity<CitaResponseDto>cambiarEstado(@PathVariable Long id, @RequestBody CitaUpdateDTO updateDTO){
//        return ResponseEntity.ok(citaStatusService.cambiarEstado(id,updateDTO));
//    }
}
