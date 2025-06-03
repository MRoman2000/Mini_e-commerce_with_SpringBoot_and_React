package com.proyect.mini_ecommerce.controller;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControladorUsuario {


    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/usuarios")
    private Usuario crearUsuario(@RequestBody Usuario usuario) {
        return servicioUsuario.crearUsuario(usuario);

    }


}
