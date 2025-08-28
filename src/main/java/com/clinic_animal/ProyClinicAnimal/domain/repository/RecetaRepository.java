package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta,Long> {
    List<Receta> findByMedicamentosContainingIgnoreCase(String nombre);
//    List<Receta> findByFechaEmisionBetween(LocalDateTime inicio, LocalDateTime fin);
}
