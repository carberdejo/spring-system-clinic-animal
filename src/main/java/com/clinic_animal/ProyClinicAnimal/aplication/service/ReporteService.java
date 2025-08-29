package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte1;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte2;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte3;

import java.util.List;
import java.util.Map;

public interface ReporteService {

        List<Reporte1> obtenerReporte1(Integer clienteId);
        List<Reporte2> obtenerReporte2(Integer clienteId);
        List<Reporte3> obtenerReporte3(Integer limite);

}
