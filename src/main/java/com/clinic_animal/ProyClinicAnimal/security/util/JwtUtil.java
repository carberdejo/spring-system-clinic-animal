package com.clinic_animal.ProyClinicAnimal.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${security.secret-key}")
    private String secretKey;
    @Value("${security.expiration}")
    private Long expiracion;

    private Key getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims =Map.of("roles",userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //Extaer toda la informacion del token
    public Claims extraerAllClaims(String token){
            return  Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }

    //TODO obtener username
    public String extraerUserName(String token){
        return  extraerAllClaims(token).getSubject();
    }

    public Date extraerExpiracion(String token){
            return  extraerAllClaims(token).getExpiration();
    }

    public boolean validarToken(String token,UserDetails userDetails){
        String username = extraerUserName(token);
        Date expiracion = extraerExpiracion(token);
        return userDetails.getUsername().equals(username) && expiracion.after(new Date());

    }
}
