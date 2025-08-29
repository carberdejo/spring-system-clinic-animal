package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.MascotaResponseDto;

import java.util.List;

public interface MascotaService {
    MascotaResponseDto crearMascota(MascotaRequestDto dto);
    MascotaResponseDto actualizarMascota(Long id, MascotaUpdateRequestDto dto);
    void eliminarMascota(Long id);
    MascotaResponseDto obtenerMascotaPorId(Long id);
    List<MascotaResponseDto> listarMascotas();
}
