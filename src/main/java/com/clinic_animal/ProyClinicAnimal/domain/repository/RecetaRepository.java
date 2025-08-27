package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta,Long> {
    List<Receta> findByNombreContainingIgnoreCase(String nombre);
    List<Receta> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
