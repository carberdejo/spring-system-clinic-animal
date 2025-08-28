package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Horario;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoHorario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    boolean existsByRolesRolCodigo(Long id);
    Horario findAllByRolesRolCodigoAndEstadoHorario(Long id, EstadoHorario estadoHorario);
}
