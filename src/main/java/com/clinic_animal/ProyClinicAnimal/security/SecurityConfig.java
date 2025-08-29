package com.clinic_animal.ProyClinicAnimal.security;

import com.clinic_animal.ProyClinicAnimal.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter authenticationFilter;
    private final CustomUserDetail customUserDetail;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/Area").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/api/Area/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PATCH,"/api/Area/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/api/Roles").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/api/Roles/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PATCH,"/api/Roles/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/api/Personal").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/api/Personal/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PATCH,"/api/Personal/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/api/servicios").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/api/servicios/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PATCH,"/api/servicios/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/api/horario").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/api/horario/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PATCH,"/api/horario/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE,"/api/horario/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/api/asistencia   ").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/api/asistencia/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PATCH,"/api/asistencia/**").hasRole("Admin")
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetail);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
