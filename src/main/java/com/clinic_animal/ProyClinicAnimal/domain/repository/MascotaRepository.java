package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {
}
