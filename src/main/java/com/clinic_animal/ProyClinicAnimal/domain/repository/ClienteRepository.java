package com.clinic_animal.ProyClinicAnimal.domain.repository;

import com.clinic_animal.ProyClinicAnimal.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
