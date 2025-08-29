package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaStatusService;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaReprogramarDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PatchMapping("/estadoCambiar/{id}")
    public ResponseEntity<CitaResponseDto>cambiarEstado(@PathVariable Long id, Authentication auth, @RequestBody CitaUpdateDTO updateDTO){
        String username = auth.getName();
        return ResponseEntity.ok(citaStatusService.cambiarEstado(username,id,updateDTO));
    }
    @PatchMapping("/enProgreso/{id}")
    public ResponseEntity<CitaResponseDto>citaEnProgreso(@PathVariable Long id, Authentication auth){
        String username = auth.getName();
        return ResponseEntity.ok(citaStatusService.citaEnProgreso(username,id));
    }
    @PatchMapping("/terminado/{id}")
    public ResponseEntity<CitaResponseDto>citaTerminada(@PathVariable Long id, Authentication auth){
        String username = auth.getName();
        return ResponseEntity.ok(citaStatusService.citaTerminada(username,id));
    }
    @PatchMapping("/reprogramar/{id}")
    public ResponseEntity<CitaResponseDto>citaReProgramada(@PathVariable Long id, Authentication auth, @RequestBody CitaReprogramarDto fecha){
        String username = auth.getName();
        return ResponseEntity.ok(citaStatusService.citaReProgramada(username,id,fecha));
    }
}
