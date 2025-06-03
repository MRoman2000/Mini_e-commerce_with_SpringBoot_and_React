package com.proyect.mini_ecommerce.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")  // aquí se define la clave foránea
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedidos = new ArrayList<>();


    public Pedido(Integer id, String usuario_id, LocalDate fecha, Double total) {
        this.id = id;

        this.fecha = fecha;
        this.total = total;
    }

    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
