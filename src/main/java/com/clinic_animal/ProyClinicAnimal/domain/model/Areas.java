package com.clinic_animal.ProyClinicAnimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "Areas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Areas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CodigoArea")
    private Long codigoArea;
    @Column(name="NombreArea")
    private String nomArea;
    @Column(name="Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "areas",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Personal> personalList;
}
