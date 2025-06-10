package com.proyect.mini_ecommerce.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    private Key getSigningKey() {
        String SECRET = "clave-secreta-supersegura-muy-larga-32+";
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // Generar Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraer nombre de usuario (subject)
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extraer fecha de expiración
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // Validar si el token está expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validar token completamente (firma + expiración)
    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token); // también lanzará excepción si la firma es inválida
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extraer todos los claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
