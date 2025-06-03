package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ControladorUsuario {


    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/usuarios")
    private Usuario crearUsuario(@RequestBody Usuario usuario) {
        return servicioUsuario.crearUsuario(usuario);

    }

    @GetMapping("/usuarios/me")
    private ResponseEntity<UserDto> mostrarDatos(Authentication authentication) {
        String username = authentication.getName();

        UserDto usuario = servicioUsuario.findByUsername(username);

        return  ResponseEntity.ok(usuario);
    }

}
