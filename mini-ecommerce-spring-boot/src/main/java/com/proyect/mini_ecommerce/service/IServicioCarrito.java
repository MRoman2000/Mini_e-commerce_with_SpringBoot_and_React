package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.CarritoItemDTO;
import com.proyect.mini_ecommerce.modelo.Carrito;

import java.util.List;

public interface IServicioCarrito  {

    public List<Carrito> listarCarrito();

    public void agregarProductoACarrito(Integer usuarioId, Integer productoId, Integer cantidad);

    List<CarritoItemDTO> obtenerCarritoPorUsuario(String username);
}
