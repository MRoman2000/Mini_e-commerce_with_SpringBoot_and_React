package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.CarritoItemDTO;
import com.proyect.mini_ecommerce.modelo.Carrito;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IServicioCarrito {

    public List<Carrito> listarCarrito();

    public void agregarProductoACarrito(Integer usuarioId, Integer productoId, Integer cantidad);

    List<CarritoItemDTO> obtenerCarritoPorUsuario(String username);

    void eliminarCarritoPorUsuario(String username);

    public void eliminarItemCarrito(Integer id);
}
