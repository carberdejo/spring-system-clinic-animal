package com.clinic_animal.ProyClinicAnimal.domain.model;

import com.clinic_animal.ProyClinicAnimal.domain.model.estados.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long id;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false)
    private EstadoCita estado;

    @ManyToOne
    @JoinColumn(name = "id_area",referencedColumnName = "Codigo_Area",nullable = false)
    private Areas area;

    @ManyToOne
    @JoinColumn(name = "id_mascota",referencedColumnName = "id_mascota",nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "id_cliente",referencedColumnName = "id_cliente",nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_recepcionista",referencedColumnName = "id_personal",nullable = false)
    private Personal recepcionista;

    @ManyToOne
    @JoinColumn(name = "id_veterinario",referencedColumnName = "id_personal")
    private Personal veterinario;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, optional = true)
    private Receta receta;
}
