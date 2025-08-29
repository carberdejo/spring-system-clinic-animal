package com.clinic_animal.ProyClinicAnimal.web.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private String token;
    private String username;
    private List<String> rol;
    private Long expiration;
}
