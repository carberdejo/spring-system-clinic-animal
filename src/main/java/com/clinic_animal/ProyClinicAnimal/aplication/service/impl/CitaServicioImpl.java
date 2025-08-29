package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.aplication.mapper.CitaMaper;
import com.clinic_animal.ProyClinicAnimal.aplication.service.CitaService;
import com.clinic_animal.ProyClinicAnimal.domain.model.*;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import com.clinic_animal.ProyClinicAnimal.domain.repository.*;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.CitaUpdateDTO;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.CitaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServicioImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final PersonalRepository personalRepository;
    private final MascotaRepository mascotaRepository;
    private final CitaMaper citaMaper;
    private final AreaRepositry areaRepositry;

    @Override
    public CitaResponseDto crear(CitaRequestDto citaRequestDTO) {
        Mascota mascota = mascotaRepository.findById(citaRequestDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        Cliente cliente = clienteRepository.findById(citaRequestDTO.getIdCliente()).
                orElseThrow(()-> new ErrorNegocio("Cliente con ID"+ citaRequestDTO.getIdCliente()+" no encontrado"));
        Personal recepcionista = personalRepository.findById(citaRequestDTO.getIdRecepcionista())
                .orElseThrow(()->new ErrorNegocio("Recepcionista con ID "+" no encontrado"));
        Personal veterinario = personalRepository.findById(citaRequestDTO.getIdVeterinario())
                .orElseThrow(()->new ErrorNegocio("Veterinario con ID "+citaRequestDTO.getIdVeterinario()+" no encontrado"));
        Areas areas = areaRepositry.findById(citaRequestDTO.getIdArea())
                .orElseThrow(()->new ErrorNegocio("Area con ID "+citaRequestDTO.getIdArea()+" no encontrado"));

        if(citaRequestDTO.getEstadoCita().validarEstadoCrear()){
            throw new ErrorNegocio("Este metodo no es permitido para crear una cita");
        }

        Cita cita = citaMaper.toEntity(citaRequestDTO,mascota,cliente,recepcionista,veterinario,areas);

        return citaMaper.toDto(citaRepository.save(cita));

    }

    @Override
    public CitaResponseDto obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .map(citaMaper::toDto)
                .orElseThrow(() -> new ErrorNegocio("Cita no encontrada"));

    }

    @Override
    public List<CitaResponseDto> listar(EstadoCita updateDTO) {

        if(updateDTO != null){
            return citaRepository.findAllByEstado(updateDTO).stream()
                    .map(citaMaper::toDto).toList();
        }
        return citaRepository.findAll().stream()
                .map(citaMaper::toDto).toList();

    }

    @Override
    public List<CitaResponseDto> listarPorMascota(Long mascotaId) {
        return citaRepository.findAllByMascotaId(mascotaId).stream()
                .map(citaMaper::toDto).toList();
    }


<<<<<<< HEAD
=======
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
                .nombreMascota(cita.getMascota().getNombreMascota())
                .tieneReceta(cita.getReceta() != null)
                .build();
    }
>>>>>>> 96f3ba0 (Super Cambios)
}

