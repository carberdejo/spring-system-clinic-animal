package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta,Long> {
}
