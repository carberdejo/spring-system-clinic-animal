package com.clinic_animal.ProyClinicAnimal.aplication.service;

import com.clinic_animal.ProyClinicAnimal.web.dto.request.LoginRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto requestDto);
}
