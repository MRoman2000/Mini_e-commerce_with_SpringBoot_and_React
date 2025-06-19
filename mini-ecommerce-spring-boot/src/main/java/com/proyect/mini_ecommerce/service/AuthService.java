package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.auth.AuthRequest;
import com.proyect.mini_ecommerce.auth.AuthResponse;
import com.proyect.mini_ecommerce.modelo.CustomUserDetails;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest request, HttpServletResponse response) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Usuario usuario = new Usuario();
        usuario.setId(userDetails.getId());
        usuario.setUsername(userDetails.getUsername());
        usuario.setEmail(userDetails.getEmail());
        usuario.setRoles(userDetails.getRoles());

        String accessToken = jwtUtil.generateAccessToken(usuario);
        String refreshToken = jwtUtil.generateRefreshToken(usuario);

        // Crear la cookie HttpOnly
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();

        // ENVIAR la cookie en la respuesta
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return new AuthResponse(accessToken);
    }


}
