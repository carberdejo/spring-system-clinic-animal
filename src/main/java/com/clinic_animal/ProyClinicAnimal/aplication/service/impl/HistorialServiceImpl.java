package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.service.HistorialService;
import com.clinic_animal.ProyClinicAnimal.domain.model.Mascota;
import com.clinic_animal.ProyClinicAnimal.domain.repository.MascotaRepository;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.HistorialMedicoDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialServiceImpl implements HistorialService {
    private final MascotaRepository mascotaRep;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final EntityManager entityManager;
    @Override
    public HistorialMedicoDto obtenerHistorialPorMascota(Long idMascota) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("Historial");

        query.registerStoredProcedureParameter("id_mascota", Long.class, ParameterMode.IN);
        query.setParameter("id_mascota", idMascota);

        List<Object[]> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null; // O lanzar excepción si prefieres
        }

        Object[] fila = resultList.get(0);

        HistorialMedicoDto dto = new HistorialMedicoDto();
        dto.setIdMascota(((Number) fila[0]).longValue());
        dto.setNombreMascota((String) fila[1]);
        dto.setEspecie((String) fila[2]);
        dto.setRaza((String) fila[3]);
        dto.setEdad(((Number) fila[4]).intValue());
        dto.setSexo((String) fila[5]);
        dto.setIdCliente(fila[6] != null ? ((Number) fila[6]).longValue() : null);
        dto.setNombreCliente((String) fila[7]);
        dto.setDniCliente((String) fila[8]);
        dto.setTotalCitas(((Number) fila[9]).intValue());
        dto.setFechaPrimeraCita(fila[10] != null ? ((Timestamp) fila[10]).toLocalDateTime() : null);
        dto.setFechaUltimaCita(fila[11] != null ? ((Timestamp) fila[11]).toLocalDateTime() : null);
        // si agregaste primera cita en el procedure

        return dto;
    }
}
