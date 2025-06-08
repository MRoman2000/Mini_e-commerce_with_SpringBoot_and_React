package com.proyect.mini_ecommerce.dto;

import java.math.BigDecimal;

public class CarritoItemDTO {
    private  Integer id;
    private Integer productoId;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer cantidad;
    private String imagenUrl;

    // Constructor vacío (requerido para Jackson)
    public CarritoItemDTO() {}

    public CarritoItemDTO(Integer id, Integer productoId, String nombre, String descripcion, BigDecimal precio, Integer cantidad, String imagenUrl) {
        this.id = id;
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagenUrl = imagenUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
// Getters y setters

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
