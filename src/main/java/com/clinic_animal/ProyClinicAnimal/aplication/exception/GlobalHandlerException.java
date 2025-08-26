package com.clinic_animal.ProyClinicAnimal.aplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(ErrorNegocio.class)
    public ResponseEntity<Object>handlerBusinessException(ErrorNegocio errorNegocio){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errorNegocio.getMessage(), LocalDateTime.now()));
    }
}
