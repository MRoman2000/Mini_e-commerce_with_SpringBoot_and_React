package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.ListaDeseos;
import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioListaDeseos;
import com.proyect.mini_ecommerce.repository.RepositorioProducto;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Set;

@Service
public class ServicioListaDeseos implements IServicioListaDeseos {

    @Autowired
    private RepositorioListaDeseos repositorioListaDeseos;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioUsuario repositorioUsuario;


    @Override
    public Set<Producto> obtenerWishlistPorUsuario(Integer id) {
        return repositorioListaDeseos.findByUsuarioId(id)
                .map(ListaDeseos::getProductos)
                .orElse(Collections.emptySet());
    }


    @Override
    public void agregarProducto(Integer usuarioId, Integer productoId) {
        ListaDeseos listaDeseos = repositorioListaDeseos.findByUsuarioId(usuarioId).orElseGet(() -> {
            ListaDeseos nueva = new ListaDeseos();
            Usuario usuario = repositorioUsuario.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            nueva.setUsuario(usuario);
            return repositorioListaDeseos.save(nueva);
        });

        Producto producto = repositorioProducto.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!listaDeseos.getProductos().contains(producto)) {
            listaDeseos.getProductos().add(producto);
        }


        repositorioListaDeseos.save(listaDeseos);
    }


    @Override
    public void eliminarProducto(Integer usuarioId, Integer productoId) {
        ListaDeseos listaDeseos = repositorioListaDeseos.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        Producto producto = repositorioProducto.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        listaDeseos.getProductos().remove(producto);
        repositorioListaDeseos.save(listaDeseos);
    }
}
