package com.clinic_animal.ProyClinicAnimal.web.controller;


import com.clinic_animal.ProyClinicAnimal.aplication.service.ReporteService;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte1;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte2;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte3;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Reportes")
@RequiredArgsConstructor
public class ReportesController {

    private final ReporteService reporteService;
    @GetMapping("/reporte1")
    public ResponseEntity<List<Reporte1>> ObtenerReporte1(@RequestParam(required = false) Integer clienteId) {
        return ResponseEntity.ok(reporteService.obtenerReporte1(clienteId));
    }
    @GetMapping("/reporte2")
    public ResponseEntity<List<Reporte2>> ObtenerReporte2(@RequestParam(required = false) Integer clienteId) {
        return ResponseEntity.ok(reporteService.obtenerReporte2(clienteId));
    }
    @GetMapping("/reporte3")
    public ResponseEntity<List<Reporte3>> ObtenerReporte3(@RequestParam(required = false) Integer limite) {
        return ResponseEntity.ok(reporteService.obtenerReporte3(limite));
    }
}
