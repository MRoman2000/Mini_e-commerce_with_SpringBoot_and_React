package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.dto.PedidoDTO;
import com.proyect.mini_ecommerce.dto.ProductoCantidadDTO;
import com.proyect.mini_ecommerce.modelo.CustomUserDetails;
import com.proyect.mini_ecommerce.service.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ControladorPedido {


    @Autowired
    private ServicioPedido servicioPedido;

    @PostMapping("/pedidos")
    public ResponseEntity<?> crearPedido(@RequestBody List<ProductoCantidadDTO> productos,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getId();
        servicioPedido.crearPedido(userId, productos);
        return ResponseEntity.ok("Pedido creado correctamente.");
    }


    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidosUsuario(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getId(); // Obtenemos el ID directamente del usuario autenticado

        List<PedidoDTO> pedidos = servicioPedido.obtenerPedidosConDetallesPorUsuario(userId);

        return ResponseEntity.ok(pedidos);
    }

}
