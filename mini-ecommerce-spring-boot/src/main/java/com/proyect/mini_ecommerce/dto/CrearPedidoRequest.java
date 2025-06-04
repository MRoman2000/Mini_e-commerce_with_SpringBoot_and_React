package com.proyect.mini_ecommerce.dto;

import java.util.List;

public class CrearPedidoRequest {
    private List<ProductoCantidadDTO> productosCantidad;

    public CrearPedidoRequest() {}

    public List<ProductoCantidadDTO> getProductosCantidad() {
        return productosCantidad;
    }

    public void setProductosCantidad(List<ProductoCantidadDTO> productosCantidad) {
        this.productosCantidad = productosCantidad;
    }
}
