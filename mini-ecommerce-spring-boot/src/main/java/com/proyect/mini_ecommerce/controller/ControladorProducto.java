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
    public List<Producto> getProductos() {
        return servicioProducto.listarProductos();
    }


    @PostMapping("/agregar")
    public Producto agregarProducto(@RequestBody Producto producto) {
        return servicioProducto.agregarProducto(producto);
    }

    @GetMapping("/buscar")
    public List<Producto> buscarProducto(@RequestParam(required = true) String nombre) {
        if (nombre != null) {
            return servicioProducto.findByNombre(nombre);
        }
        return getProductos();
    }


}
