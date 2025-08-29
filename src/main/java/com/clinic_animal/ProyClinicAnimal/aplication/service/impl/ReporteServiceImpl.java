package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.service.ReporteService;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte1;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte2;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.Reporte3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Reporte1> obtenerReporte1(Integer clienteId) {
        String sql = "CALL Reporte01(?)";

        return jdbcTemplate.query(sql, new Object[]{clienteId}, (rs, rowNum) -> {
            Reporte1 dto = new Reporte1();
            dto.setId(rs.getLong("id_cliente"));
            dto.setNombre(rs.getString("nombre"));
            dto.setTelefono(rs.getString("telefono"));
            dto.setEmail(rs.getString("email"));
            dto.setNombreMascota(rs.getString("nom_mascota"));
            dto.setEspecie(rs.getString("especie"));
            dto.setRaza(rs.getString("raza"));
            dto.setEdad(rs.getInt("edad"));
            dto.setSexo(rs.getString("sexo"));
            return dto;
        });
    }


    @Override
    public List<Reporte2> obtenerReporte2(Integer clienteId) {
        String sql = "CALL ReporteServicios(?)";

        return jdbcTemplate.query(sql, new Object[]{clienteId}, (rs, rowNum) -> {
            Reporte2 dto = new Reporte2();
            dto.setId_cita(rs.getLong("id_cita"));
            dto.setFechaHora(rs.getTimestamp("Fecha").toLocalDateTime());
            dto.setId_cliente(rs.getLong("id_cliente"));
            dto.setNombre(rs.getString("Cliente"));
            dto.setApellido(rs.getString("Apellido_Cliente"));
            dto.setNomMascota(rs.getString("Mascota"));
            dto.setNombreServicio(rs.getString("Servicio"));
            dto.setCantidad(rs.getInt("cantidad"));
            dto.setPrecioBase(rs.getDouble("precio_Base"));
            dto.setSubTotal(rs.getDouble("sub_Total"));
            dto.setNomArea(rs.getString("Area"));
            return dto;
        });
    }

    @Override
    public List<Reporte3> obtenerReporte3(Integer limite) {
        String sql = "CALL ReporteServiciosMasSolicitados(?)";

        return jdbcTemplate.query(sql, new Object[]{limite}, (rs, rowNum) -> {
            Reporte3 dto = new Reporte3();
            dto.setCodigoServicio(rs.getLong("cod_servicio"));
            dto.setNomServicio(rs.getString("Servicio"));
            dto.setNomArea(rs.getString("Area"));
            dto.setTotalSolicitudes(rs.getInt("Total_Solicitudes"));
            dto.setTotalFacturado(rs.getBigDecimal("Total_Facturado"));
            return dto;
        });
    }
}

