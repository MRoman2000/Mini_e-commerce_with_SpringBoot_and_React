package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.dto.AgregarCarritoRequest;
import com.proyect.mini_ecommerce.dto.CarritoItemDTO;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import com.proyect.mini_ecommerce.service.ServicioCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/carrito")
@CrossOrigin("http://localhost:3000")
@RestController
public class ControladorCarrito {

    @Autowired
    private ServicioCarrito servicioCarrito;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @GetMapping
    public List<CarritoItemDTO> obtenerCarrito(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return servicioCarrito.obtenerCarritoPorUsuario(username);
    }

    @PostMapping
    public void agregarCarrito(@RequestBody AgregarCarritoRequest request,
                               @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        Usuario usuario = repositorioUsuario.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        servicioCarrito.agregarProductoACarrito(usuario.getId(), request.getProductoId(), request.getCantidad());
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarCarritoDelUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        servicioCarrito.eliminarCarritoPorUsuario(username);
        return ResponseEntity.ok("Carrito del usuario eliminado correctamente.");
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> eliminarItemDelCarrito(@PathVariable Integer id) {
        servicioCarrito.eliminarItemCarrito(id);
        return ResponseEntity.ok().build();
    }

}
