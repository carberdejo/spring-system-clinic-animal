package com.clinic_animal.ProyClinicAnimal.aplication.service.impl;

import com.clinic_animal.ProyClinicAnimal.aplication.service.AuthService;
import com.clinic_animal.ProyClinicAnimal.security.util.JwtUtil;
import com.clinic_animal.ProyClinicAnimal.web.dto.request.LoginRequestDto;
import com.clinic_animal.ProyClinicAnimal.web.dto.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(),requestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        Long expiracion = jwtUtil.extraerExpiracion(token).getTime();

        return LoginResponseDto.builder()
                .token(token)
                .username(userDetails.getUsername())
                .expiration(expiracion)
                .rol(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
