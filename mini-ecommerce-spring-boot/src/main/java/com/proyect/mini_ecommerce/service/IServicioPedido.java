package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.PedidoDTO;
import com.proyect.mini_ecommerce.dto.ProductoCantidadDTO;
import com.proyect.mini_ecommerce.modelo.Pedido;

import java.util.List;

public interface IServicioPedido {

    public void crearPedido(Integer usuarioId, List<ProductoCantidadDTO> productosCantidad);

    List<PedidoDTO> obtenerPedidosConDetallesPorUsuario(Integer id);
}
