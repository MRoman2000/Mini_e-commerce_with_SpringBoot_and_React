package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.dto.CrearPedidoRequest;
import com.proyect.mini_ecommerce.dto.ProductoCantidadDTO;
import com.proyect.mini_ecommerce.modelo.Pedido;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import com.proyect.mini_ecommerce.service.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ControladorPedido {

    @Autowired
    private ServicioPedido servicioPedido;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @PostMapping("/pedidos")
    public ResponseEntity<?> crearPedido(@RequestBody List<ProductoCantidadDTO> productos,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Usuario usuario = repositorioUsuario.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        servicioPedido.crearPedido(usuario.getId(), productos);
        return ResponseEntity.ok("Pedido creado correctamente.");
    }

}
