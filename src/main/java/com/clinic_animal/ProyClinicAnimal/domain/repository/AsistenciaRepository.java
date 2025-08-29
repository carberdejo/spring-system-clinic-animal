package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Asistencia;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {
    List<Asistencia> findAllByPersonalId(Long id);
    boolean existsByFechaAndPersonalId(LocalDate fecha,Long id);
    Optional<Asistencia> findByFechaAndPersonalId(LocalDate fecha, Long id);
    List<Asistencia> findAllByEstadoAsistencia(EstadoAsistencia estadoAsistencia);
    List<Asistencia> findAllByEstadoAsistenciaAndPersonalId(EstadoAsistencia estadoAsistencia,Long id);
}
