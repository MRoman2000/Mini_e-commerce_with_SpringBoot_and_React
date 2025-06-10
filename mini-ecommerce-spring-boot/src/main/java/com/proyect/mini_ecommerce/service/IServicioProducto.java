package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.Producto;

import java.util.List;

public interface IServicioProducto {

    public List<Producto> listarProductos();

    public Producto agregarProducto(Producto producto);

    public Producto buscarProductoPorId(Integer id);

    public void eliminarProducto(Integer id);

    public Producto actualizarProducto(Integer id, Producto producto);

    public  List<Producto> findByNombre(String nombre);
}
