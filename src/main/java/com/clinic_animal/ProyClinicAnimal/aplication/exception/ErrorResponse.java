package com.clinic_animal.ProyClinicAnimal.aplication.exception;


import java.time.LocalDateTime;

public record ErrorResponse (String mensaje, LocalDateTime fecha){}