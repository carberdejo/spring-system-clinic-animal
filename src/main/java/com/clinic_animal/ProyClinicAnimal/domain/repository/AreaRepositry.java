package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Areas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepositry extends JpaRepository<Areas,Long> {
}
