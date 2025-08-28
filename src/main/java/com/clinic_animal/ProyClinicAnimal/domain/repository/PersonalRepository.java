package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal,Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);

    List<Personal> findAllByEstadoPersonalNot(EstadoPersonal estado);
    List<Personal> findAllByRoles_RolCodigoAndEstadoPersonalNot(Long rolCodigo, EstadoPersonal estadoPersonal);
    List<Personal> findAllByAreas_CodigoAreaAndEstadoPersonalNot(Long codigoArea, EstadoPersonal estadoPersonal);
}
