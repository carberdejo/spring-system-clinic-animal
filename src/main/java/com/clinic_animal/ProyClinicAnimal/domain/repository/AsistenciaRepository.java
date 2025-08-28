package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {
    List<Asistencia> findAllByPersonalId(Long id);
    boolean existsByFechaAndPersonalId(LocalDate fecha,Long id);
    Asistencia findByFechaAndPersonalId(LocalDate fecha,Long id);
}
