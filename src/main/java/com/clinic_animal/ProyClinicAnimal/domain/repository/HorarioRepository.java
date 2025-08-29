package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Horario;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoHorario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    boolean existsByRolesRolCodigo(Long id);
    Horario findByRolesRolCodigoAndEstadoHorario(Long id, EstadoHorario estadoHorario);
    List<Horario> findAllByEstadoHorario( EstadoHorario estadoHorario);
    List<Horario> findAllByRolesRolCodigo( Long id);
}
