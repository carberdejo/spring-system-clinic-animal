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

import java.util.ArrayList;
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
    private final ServiciosRepository serviciosRepository;
    private final CitaServicioRepository citaServicioRepository;

    @Override
    public CitaResponseDto crear(CitaRequestDto citaRequestDTO) {
        Mascota mascota = mascotaRepository.findById(citaRequestDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        Cliente cliente = clienteRepository.findById(citaRequestDTO.getIdCliente())
                .orElseThrow(() -> new ErrorNegocio("Cliente con ID " + citaRequestDTO.getIdCliente() + " no encontrado"));
        Personal recepcionista = personalRepository.findById(citaRequestDTO.getIdRecepcionista())
                .orElseThrow(() -> new ErrorNegocio("Recepcionista con ID " + citaRequestDTO.getIdRecepcionista() + " no encontrado"));
        Personal veterinario = personalRepository.findById(citaRequestDTO.getIdVeterinario())
                .orElseThrow(() -> new ErrorNegocio("Veterinario con ID " + citaRequestDTO.getIdVeterinario() + " no encontrado"));
        Areas area = areaRepositry.findById(citaRequestDTO.getIdArea())
                .orElseThrow(() -> new ErrorNegocio("Area con ID " + citaRequestDTO.getIdArea() + " no encontrado"));
        if(!veterinario.getAreas().getCodigoArea().equals(citaRequestDTO.getIdArea()))
            throw new ErrorNegocio("Ese veterinario no pertenece a esa area :(");
        if(citaRequestDTO.getEstadoCita().validarEstadoCrear()){
            throw new ErrorNegocio("Este metodo no es permitido para crear una cita");
        }

        // Crear la cita
        Cita cita = citaMaper.toEntity(citaRequestDTO, mascota, cliente, recepcionista, veterinario, area);
        cita = citaRepository.save(cita); // Guardar primero para que tenga ID
        if (cita.getCitaServicios() == null) {
            cita.setCitaServicios(new ArrayList<>());
        }
        // Si el área es 2, agregar automáticamente el servicio de chequeo
        if(area.getCodigoArea() == 2L){
            Servicios chequeoServicio = serviciosRepository.findById(3L)
                    .orElseThrow(() -> new ErrorNegocio("Servicio 'Chequeo' no encontrado"));

            CitaServicio citaServicio = new CitaServicio();
            citaServicio.setCita(cita);
            citaServicio.setServicios(chequeoServicio);
            citaServicio.setCantidad(1);
            citaServicio.setPrecioBase(chequeoServicio.getPrecio());
            citaServicio.setSubTotal(chequeoServicio.getPrecio() * citaServicio.getCantidad());

            // Asociar a la lista de la cita y guardar
            cita.getCitaServicios().add(citaServicio);
            citaServicioRepository.save(citaServicio);
        }

        // Mapear la cita a DTO incluyendo los servicios
        return citaMaper.toDto(cita);
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



}

