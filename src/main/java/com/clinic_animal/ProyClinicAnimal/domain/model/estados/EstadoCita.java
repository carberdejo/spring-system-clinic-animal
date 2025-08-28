package com.clinic_animal.ProyClinicAnimal.domain.model.estados;

public enum EstadoCita {
    PROGRAMADA,EN_COLA,EN_PROGRESO,TERMINADA,CANCELADO;

    public boolean validarEstadoCrear(){
        return this == CANCELADO || this == EN_PROGRESO || this == TERMINADA;
    }

    public boolean validarCambiarEstado(){
        return this == TERMINADA || this == EN_PROGRESO || this == CANCELADO;
    }

    public boolean validarReProgramar(){
        return this == TERMINADA || this == EN_PROGRESO;
    }
}
