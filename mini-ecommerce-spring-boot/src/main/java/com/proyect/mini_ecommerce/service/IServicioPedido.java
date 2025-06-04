package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.ProductoCantidadDTO;

import java.util.List;

public interface IServicioPedido {

    public void crearPedido(Integer usuarioId, List<ProductoCantidadDTO> productosCantidad);
}
