package com.clinic_animal.ProyClinicAnimal.web.controller;

import com.clinic_animal.ProyClinicAnimal.aplication.service.HistorialService;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HistorialMedicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialController {
    private final HistorialService historialService;
    @GetMapping("/{idMascota}")
    public ResponseEntity<HistorialMedicoDto> obtenerHistorial(@PathVariable Long idMascota) {
        return ResponseEntity.ok(historialService.obtenerHistorialPorMascota(idMascota));
    }
}
