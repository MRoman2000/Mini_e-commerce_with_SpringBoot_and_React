package com.proyect.mini_ecommerce.modelo;

import jakarta.persistence.*;

@Entity
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer pedidos_id;
    private Integer productos_id;
    private Integer cantidad;
    private Integer precio_unitario;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    public DetallePedido() {
    }


    public DetallePedido(Integer id, Integer pedidos_id, Integer productos_id, Integer cantidad, Integer precio_unitario) {
        this.id = id;
        this.pedidos_id = pedidos_id;
        this.productos_id = productos_id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public Integer getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Integer precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getProductos_id() {
        return productos_id;
    }

    public void setProductos_id(Integer productos_id) {
        this.productos_id = productos_id;
    }

    public Integer getPedidos_id() {
        return pedidos_id;
    }

    public void setPedidos_id(Integer pedidos_id) {
        this.pedidos_id = pedidos_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
