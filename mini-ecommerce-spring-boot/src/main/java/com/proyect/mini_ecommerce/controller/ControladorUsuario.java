package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ControladorUsuario {


    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/auth/register")
    private ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        UserDto creado = servicioUsuario.crearUsuario(usuario);
        if (creado == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", "El username ya está ocupado"));

        }
        return ResponseEntity.ok(creado);
    }


    @GetMapping("/usuarios/me")
    private ResponseEntity<UserDto> mostrarDatos(Authentication authentication) {
        String username = authentication.getName();

        UserDto usuario = servicioUsuario.findByUsername(username);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/usuarios/{id}")
    private ResponseEntity<UserDto> actualizar(@PathVariable Integer id, @RequestBody Usuario usuarioEditar) {

        UserDto usuario = servicioUsuario.actualizarUsuario(id, usuarioEditar);

        return ResponseEntity.ok(usuario);

    }

}
