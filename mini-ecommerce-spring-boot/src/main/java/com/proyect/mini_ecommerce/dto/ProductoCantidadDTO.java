package com.proyect.mini_ecommerce.dto;

public class ProductoCantidadDTO {

    private Integer productoId;
    private Integer cantidad;

    // Constructor vacío para frameworks (Jackson)
    public ProductoCantidadDTO() {
    }

    public ProductoCantidadDTO(Integer productoId, Integer cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

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
