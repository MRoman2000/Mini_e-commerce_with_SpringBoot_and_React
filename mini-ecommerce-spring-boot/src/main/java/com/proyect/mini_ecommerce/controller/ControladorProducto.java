package com.proyect.mini_ecommerce.controller;

import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.service.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/productos")
public class ControladorProducto {

    @Autowired
    private ServicioProducto servicioProducto;

    @GetMapping
    private List<Producto> getProductos() {
        return servicioProducto.listarProductos();
    }

    
    @PostMapping
    private Producto agregarProducto(@RequestBody Producto producto) {
        return servicioProducto.agregarProducto(producto);

    }


}
