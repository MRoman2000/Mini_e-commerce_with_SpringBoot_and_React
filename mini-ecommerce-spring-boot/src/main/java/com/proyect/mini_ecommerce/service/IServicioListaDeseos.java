package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.Producto;

import java.util.Set;

public interface IServicioListaDeseos {

    Set<Producto> obtenerWishlistPorUsuario(Integer usuarioId);

    void agregarProducto(Integer usuarioId, Integer productoId);

    void eliminarProducto(Integer id, Integer productoId);
}
