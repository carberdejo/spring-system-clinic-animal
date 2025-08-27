package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.service.RecetaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import com.clinic_animal.ProyClinicAnimal.domain.repository.CitaRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.RecetaRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaUpdateDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecetaServicioImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final CitaRepository citaRepository;


    @Override
    public RecetaResponseDto crear(RecetaRequestDto recetaRequestDTO) {
        Cita cita = citaRepository.findById(recetaRequestDTO.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + recetaRequestDTO.getCitaId()));

        LocalDateTime fecha = recetaRequestDTO.getFechaEmision() != null
                ? recetaRequestDTO.getFechaEmision()
                : LocalDateTime.now();

        Receta receta = Receta.builder()
                .fechaEmision(fecha)
                .indicaciones(recetaRequestDTO.getIndicaciones())
                .medicamentos(recetaRequestDTO.getMedicamentos())
                .cita(cita)
                .build();

        return mapToResponse(recetaRepository.save(receta));

    }

    @Override
    public RecetaResponseDto obtenerPorId(Long id) {
        return recetaRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con id: " + id));

    }

    @Override
    public List<RecetaResponseDto> listar() {
        return recetaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaResponseDto> buscarPorNombre(String nombre) {
        return recetaRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaResponseDto> buscarPorFecha(LocalDate inicio, LocalDate fin) {

        return recetaRepository.findByFechaBetween(inicio, fin).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public RecetaResponseDto actualizar(Long id, RecetaUpdateDto recetaUpdateDTO) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con id: " + id));

        if (recetaUpdateDTO.getIndicaciones() != null) {
            receta.setIndicaciones(recetaUpdateDTO.getIndicaciones());
        }
        if (recetaUpdateDTO.getMedicamentos() != null) {
            receta.setMedicamentos(recetaUpdateDTO.getMedicamentos());
        }

        return mapToResponse(recetaRepository.save(receta));
    }

    @Override
    public void eliminar(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con id: " + id);
        }
        recetaRepository.deleteById(id);

    }


    private RecetaResponseDto mapToResponse(Receta receta) {
        Long citaId = (receta.getCita() != null) ? receta.getCita().getId() : null;
        String nombrePaciente = null;
        if (receta.getCita() != null && receta.getCita().getMascota() != null) {
            nombrePaciente = receta.getCita().getMascota().getNombre();
        }

        return RecetaResponseDto.builder()
                .id(receta.getId())
                .fechaEmision(receta.getFechaEmision())
                .indicaciones(receta.getIndicaciones())
                .medicamentos(receta.getMedicamentos())
                .citaId(citaId)
                .nombrePaciente(nombrePaciente)
                .build();
    }
}
