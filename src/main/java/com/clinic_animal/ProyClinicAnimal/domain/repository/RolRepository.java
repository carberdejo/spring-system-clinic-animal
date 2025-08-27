package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Roles,Long> {
    boolean existsByRolNombre(String Roles);
    Roles findByRolNombre(String rolNombre);
}
