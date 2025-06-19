package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.ListaDeseos;
import com.proyect.mini_ecommerce.modelo.Producto;

import java.util.List;
import java.util.Set;

public interface IServicioListaDeseos {

    public Set<Producto> obtenerWishlistPorUsuario(Integer usuarioId);

    public void agregarProducto(Integer usuarioId, Integer productoId);

    public void eliminarProducto(Integer id, Integer productoId);
}
