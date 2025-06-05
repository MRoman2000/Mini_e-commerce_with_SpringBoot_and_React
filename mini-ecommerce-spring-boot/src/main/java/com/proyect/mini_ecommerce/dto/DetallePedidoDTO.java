package com.proyect.mini_ecommerce.dto;

import java.math.BigDecimal;

public class DetallePedidoDTO {
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private  String imagenUrl;

    // Constructor

    public DetallePedidoDTO(String nombreProducto, int cantidad, BigDecimal precioUnitario, String imagenUrl) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.imagenUrl = imagenUrl;
    }


    // Getters y setters

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
