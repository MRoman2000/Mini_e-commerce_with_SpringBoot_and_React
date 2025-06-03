package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.repository.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProducto implements IServicioProducto {


    @Autowired
    private RepositorioProducto repositorioProducto;


    @Override
    public List<Producto> listarProductos() {
        return repositorioProducto.findAll();
    }


    @Override
    public Producto agregarProducto(Producto producto) {
        return repositorioProducto.save(producto);
    }

    @Override
    public Producto buscarProductoPorId(Integer id) {
        return repositorioProducto.findById(id).orElse(null);
    }

    @Override
    public void eliminarProducto(Integer id) {
        repositorioProducto.deleteById(id);
    }

    @Override
    public Producto actualizarProducto(Integer id, Producto producto) {
        Producto buscarProducto = repositorioProducto.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        buscarProducto.setDescripcion(producto.getDescripcion());
        buscarProducto.setNombre(producto.getNombre());
        buscarProducto.setPrecio(producto.getPrecio());
        buscarProducto.setImagenUrl(producto.getImagenUrl());


        return repositorioProducto.save(buscarProducto);
    }
}
