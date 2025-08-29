package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.mapper.MascotaMapper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.MascotaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.repository.MascotaRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.MascotaUpdateRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.MascotaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;

    @Override
    public MascotaResponseDto crearMascota(MascotaRequestDto dto) {
        Mascota mascota = mascotaMapper.toEntity(dto);
        Mascota guardado = mascotaRepository.save(mascota);
        return mascotaMapper.toResponse(guardado);
    }

    @Override
    public MascotaResponseDto actualizarMascota(Long id, MascotaUpdateRequestDto dto) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id " + id));
        mascotaMapper.updateEntity(mascota, dto);
        Mascota actualizado = mascotaRepository.save(mascota);
        return mascotaMapper.toResponse(actualizado);
    }

    @Override
    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con id " + id);
        }
        mascotaRepository.deleteById(id);
    }

    @Override
    public MascotaResponseDto obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id)
                .map(mascotaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id " + id));
    }

    @Override
    public List<MascotaResponseDto> listarMascotas() {
        return mascotaRepository.findAll()
                .stream()
                .map(mascotaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
