package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.HistorialMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.HistorialService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.repository.MascotaRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HistorialMedicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistorialServiceImpl implements HistorialService {
    private final MascotaRepository mascotaRep;
    private final HistorialMapper historialMapper;
    @Override
    public HistorialMedicoDto obtenerHistorialPorMascota(Long idMascota) {
        Mascota mascota = mascotaRep.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id " + idMascota));

        return historialMapper.toHistorialMedicoDto(mascota);
    }
}
