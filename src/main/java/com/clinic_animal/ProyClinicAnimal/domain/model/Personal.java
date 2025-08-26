package com.clinic_animal.ProyClinicAnimal.domain.model;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoPersonal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Personal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Long id;
    @Column(name = "nombre_personal")
    private String nombrePersonal;
    @Column(name = "apellido_personal")
    private String apellidoPersonal;
    @Column(name = "edad")
    private int edad;
    @Column(name = "dni")
    private String dni;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
    @Column(name="Estado")
    @Enumerated (EnumType.STRING)
    private EstadoPersonal estadoPersonal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codigo_Rol")
    private Roles roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Codigo_Area")
    private Areas areas;
}
