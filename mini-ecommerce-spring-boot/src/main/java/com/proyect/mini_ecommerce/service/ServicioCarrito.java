package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.CarritoItemDTO;
import com.proyect.mini_ecommerce.modelo.Carrito;
import com.proyect.mini_ecommerce.modelo.CarritoItem;
import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioCarrito;
import com.proyect.mini_ecommerce.repository.RepositorioCarritoItem;
import com.proyect.mini_ecommerce.repository.RepositorioProducto;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioCarrito implements IServicioCarrito {


    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioCarrito repositorioCarrito;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioCarritoItem repositorioCarritoItem;

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

    @Override
    public List<CarritoItemDTO> obtenerCarritoPorUsuarioId(Integer idUsuario) {
        List<CarritoItem> carritoItems = repositorioCarritoItem.findByCarritoUsuarioId(idUsuario);

        return carritoItems.stream()
                .map(item -> new CarritoItemDTO(
                        item.getId(),
                        item.getProducto().getId(),
                        item.getProducto().getNombre(),
                        item.getProducto().getDescripcion(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getImagenUrl()
                ))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void eliminarCarritoPorUsuarioId(Integer idUser) {
        Carrito carrito = repositorioCarrito.findByUsuarioId(idUser)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        repositorioCarrito.delete(carrito); // Elimina el carrito y sus items si tienes `orphanRemoval = true`
    }


    @Override
    public void eliminarItemCarrito(Integer id) {
        repositorioCarritoItem.deleteById(id);
    }


}
