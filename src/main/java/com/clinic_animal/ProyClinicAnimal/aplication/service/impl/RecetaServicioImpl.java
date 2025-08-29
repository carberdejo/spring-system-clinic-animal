package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.RecetaMaper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.RecetaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Cita;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import com.clinic_animal.ProyClinicAnimal.domain.repository.CitaRepository;
import com.clinic_animal.ProyClinicAnimal.domain.repository.RecetaRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.RecetaUpdateDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.RecetaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecetaServicioImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final CitaRepository citaRepository;
    private final RecetaMaper recetaMaper;


    @Override
    public RecetaResponseDto crear(RecetaRequestDto recetaRequestDTO) {
        Cita cita = citaRepository.findById(recetaRequestDTO.getCitaId())
                .orElseThrow(() -> new ErrorNegocio("Cita no encontrada con id: " + recetaRequestDTO.getCitaId()));

        if (cita.getReceta() != null) {
            throw new ErrorNegocio("La cita con id " + cita.getId() + " ya tiene una receta registrada.");
        }

        Receta receta = recetaMaper.toEntity(recetaRequestDTO,cita);

        return recetaMaper.toDto(recetaRepository.save(receta));

    }

    @Override
    public RecetaResponseDto obtenerPorId(Long id) {
        return recetaRepository.findById(id)
                .map(recetaMaper::toDto)
                .orElseThrow(() -> new ErrorNegocio("Receta no encontrada con id: " + id));

    }

    @Override
    public List<RecetaResponseDto> listar() {
        return recetaRepository.findAll().stream()
                .map(recetaMaper::toDto).toList();
    }

    @Override
    public List<RecetaResponseDto> buscarPorCantidad(Integer cantidad) {
        List<Receta> recetas = recetaRepository.findAllByCantidadGreaterThanEqual(cantidad);
        return recetas.stream()
                .map(recetaMaper::toDto)
                .toList()
                ;

    }


    @Override
    public RecetaResponseDto actualizar(Long id, RecetaUpdateDto recetaUpdateDTO) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ErrorNegocio("Receta no encontrada con id: " + id));

        // Guardamos referencia de la cita original (para no romper la relación única)
        Cita citaOriginal = receta.getCita();

        // Solo actualizamos campos simples
        if (recetaUpdateDTO.getIndicaciones() != null) {
            receta.setIndicaciones(recetaUpdateDTO.getIndicaciones());
        }
        if (recetaUpdateDTO.getCantidad() != null) {
            receta.setCantidad(recetaUpdateDTO.getCantidad());
        }
        if (recetaUpdateDTO.getMedicamentos() != null) {
            receta.setMedicamentos(recetaUpdateDTO.getMedicamentos());
        }

        // Volvemos a poner explícitamente la cita original
        receta.setCita(citaOriginal);

        Receta recetaUpdate = recetaRepository.save(receta);
        return recetaMaper.toDto(recetaUpdate);
    }



    @Override
    public void eliminar(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con id: " + id);
        }
        recetaRepository.deleteById(id);

    }



}
