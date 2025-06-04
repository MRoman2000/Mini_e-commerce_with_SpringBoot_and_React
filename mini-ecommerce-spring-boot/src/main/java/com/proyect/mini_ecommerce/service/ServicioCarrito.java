package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.Carrito;
import com.proyect.mini_ecommerce.modelo.CarritoItem;
import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioCarrito;
import com.proyect.mini_ecommerce.repository.RepositorioProducto;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCarrito implements IServicioCarrito {


    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioCarrito repositorioCarrito;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Override
    public List<Carrito> listarCarrito() {
        return repositorioCarrito.findAll();
    }

    @Transactional
    public void agregarProductoACarrito(Integer usuarioId, Integer productoId, Integer cantidad) {
        Usuario usuario = repositorioUsuario.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = repositorioCarrito.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(usuario);
                    return repositorioCarrito.save(nuevo);
                });

        Optional<CarritoItem> itemExistente = carrito.getItems().stream()
                .filter(item -> item.getProducto().getId().equals(productoId))
                .findFirst();

        Producto producto = repositorioProducto.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (itemExistente.isPresent()) {
            itemExistente.get().setCantidad(itemExistente.get().getCantidad() + cantidad);
        } else {
            CarritoItem nuevoItem = new CarritoItem();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(cantidad);
            carrito.getItems().add(nuevoItem);
        }

        repositorioCarrito.save(carrito);
    }
}
