package com.proyect.mini_ecommerce.modelo;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "wishlist_productos",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos = new HashSet<>();

    public ListaDeseos(Integer id, Usuario usuario, Set<Producto> productos) {
        this.id = id;
        this.usuario = usuario;
        this.productos = productos;
    }

    public ListaDeseos() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
