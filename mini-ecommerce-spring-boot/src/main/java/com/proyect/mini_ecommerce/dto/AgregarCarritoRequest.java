package com.proyect.mini_ecommerce.dto;

public class AgregarCarritoRequest {
    private Integer productoId;
    private Integer cantidad;



    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}