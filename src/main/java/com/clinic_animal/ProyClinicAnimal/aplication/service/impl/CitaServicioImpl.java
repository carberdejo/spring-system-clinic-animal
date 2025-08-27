package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.CitaServicio;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.domain.repository.CitaRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.MascotaRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaServicioImpl implements CitaService {

    private final CitaRepository citaRepository;

    private final MascotaRepository mascotaRepository;

    @Override
    public CitaResponseDto crear(CitaRequestDto citaRequestDTO) {
        Mascota mascota = mascotaRepository.findById(citaRequestDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Cita cita = Cita.builder()
                .fechaHora(citaRequestDTO.getFechaHora())
                .estado(EstadoCita.EN_COLA)
                .mascota(mascota)
                .build();

        return mapToResponse(citaRepository.save(cita));

    }

    @Override
    public CitaResponseDto obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

    }

    @Override
    public List<CitaResponseDto> listar() {
        return citaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<CitaResponseDto> listarPorMascota(Long mascotaId) {
        return citaRepository.findByMascotaId(mascotaId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaResponseDto> listarPorEstado(EstadoCita estado) {
        return citaRepository.findByEstado(estado).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CitaResponseDto actualizarEstado(Long id, CitaUpdateDTO citaUpdateDTO) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado(citaUpdateDTO.getEstado());

        return mapToResponse(citaRepository.save(cita));
    }

    @Override
    public void eliminar(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada");
        }
        citaRepository.deleteById(id);
    }

    private CitaResponseDto mapToResponse(Cita cita) {
        return CitaResponseDto.builder()
                .id(cita.getId())
                .fechaHora(cita.getFechaHora())
                .estado(cita.getEstado())
                .mascotaId(cita.getMascota().getId())
                .nombreMascota(cita.getMascota().getNombre())
                .tieneReceta(cita.getReceta() != null)
                .build();
    }
}

