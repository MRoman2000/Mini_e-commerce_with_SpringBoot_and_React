package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.modelo.CustomUserDetails;
import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.service.ServicioListaDeseos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin("http://localhost:3000")
public class ControladorListaDeseos {

    @Autowired
    private ServicioListaDeseos servicioListaDeseos;

    @PostMapping("/agregar/{productId}")
    public ResponseEntity<String> agregarProductoAWishlist(
            @PathVariable Integer productId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }

        Integer userId = userDetails.getId();
        servicioListaDeseos.agregarProducto(userId, productId);

        return ResponseEntity.ok("Producto agregado a la lista de deseos");
    }


    @GetMapping
    public Set<Producto> obtenerWishlist(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer id = userDetails.getId();
        return servicioListaDeseos.obtenerWishlistPorUsuario(id);
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<?> eliminarProductoLista(@PathVariable Integer productoId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer id = userDetails.getId();
        servicioListaDeseos.eliminarProducto(id, productoId);
        return ResponseEntity.ok("producto eliminado");
    }
}
