package com.clinic_animal.ProyClinicAnimal.security;

import com.clinic_animal.ProyClinicAnimal.aplication.exception.ErrorNegocio;
import com.clinic_animal.ProyClinicAnimal.domain.model.Personal;
import com.clinic_animal.ProyClinicAnimal.domain.repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetailsService {
    private final PersonalRepository personalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personal personal =personalRepository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("Usuario no encontrado"));
        return User.builder()
                .username(personal.getEmail())
                .password(personal.getContraseña())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_"+personal.getRoles().getRolNombre()))).build();

    }
}
