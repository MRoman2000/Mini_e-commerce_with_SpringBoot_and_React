package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.dto.AgregarCarritoRequest;
import com.proyect.mini_ecommerce.dto.CarritoItemDTO;
import com.proyect.mini_ecommerce.modelo.CustomUserDetails;
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
    public List<CarritoItemDTO> obtenerCarrito(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return servicioCarrito.obtenerCarritoPorUsuarioId(userDetails.getId());
    }


    @PostMapping
    public ResponseEntity<?> agregarCarrito(@RequestBody AgregarCarritoRequest request,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer idUsuario = userDetails.getId();
        servicioCarrito.agregarProductoACarrito(idUsuario, request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    public ResponseEntity<?> eliminarCarritoDelUsuario(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getId();
        servicioCarrito.eliminarCarritoPorUsuarioId(userId);
        return ResponseEntity.ok("Carrito del usuario eliminado correctamente.");
    }


    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> eliminarItemDelCarrito(@PathVariable Integer id) {
        servicioCarrito.eliminarItemCarrito(id);
        return ResponseEntity.ok().build();
    }

}
