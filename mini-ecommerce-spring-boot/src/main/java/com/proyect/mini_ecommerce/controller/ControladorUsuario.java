package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.auth.AuthResponse;
import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.CustomUserDetails;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.security.JwtUtil;
import com.proyect.mini_ecommerce.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ControladorUsuario {
    
    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth/register")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        UserDto creado = servicioUsuario.crearUsuario(usuario);
        if (creado == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", "El username ya está ocupado"));
        }

        return ResponseEntity.ok(creado);
    }


    @GetMapping("/usuarios/me")
    public ResponseEntity<UserDto> mostrarDatos(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UserDto usuario = new UserDto();
        usuario.setUsername(userDetails.getUsername());
        usuario.setId(userDetails.getId());
        usuario.setEmail(userDetails.getEmail());

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/admin")
    public List<Usuario> obtenerTodosUsuarios() {
        return servicioUsuario.listarUsuario();
    }


    @PutMapping("/usuarios/{id}")
    private ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuario usuarioEditar) {
        Usuario usuarioActualizado = servicioUsuario.actualizarUsuario(id, usuarioEditar);

        boolean requiereNuevoToken = usuarioEditar.getRoles() != null &&
                !usuarioEditar.getRoles().equals(usuarioActualizado.getRoles());

        if (requiereNuevoToken) {
            String nuevoToken = jwtUtil.generateAccessToken(usuarioActualizado);
            return ResponseEntity.ok(new AuthResponse(nuevoToken));
        }

        return ResponseEntity.ok(usuarioActualizado);

    }

}
