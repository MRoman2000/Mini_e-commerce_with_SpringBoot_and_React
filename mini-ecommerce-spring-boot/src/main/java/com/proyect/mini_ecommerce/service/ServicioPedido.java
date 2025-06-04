package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.ProductoCantidadDTO;
import com.proyect.mini_ecommerce.modelo.DetallePedido;
import com.proyect.mini_ecommerce.modelo.Pedido;
import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioPedido;
import com.proyect.mini_ecommerce.repository.RepositorioProducto;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPedido implements IServicioPedido {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioPedido repositorioPedido;


    @Transactional
    public void crearPedido(Integer usuarioId, List<ProductoCantidadDTO> productosCantidad) {
        Usuario usuario = repositorioUsuario.findById(usuarioId).orElseThrow(() -> new RuntimeException("No encontrado el usuario"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<DetallePedido> detalles = new ArrayList<>();


        for (ProductoCantidadDTO pc : productosCantidad) {
            Producto producto = repositorioProducto.findById(pc.getProductoId()).orElseThrow(() -> new RuntimeException("no hay producto"));

            if (producto.getStock() < pc.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para producto: " + producto.getNombre());
            }

            // Actualizar stock
            producto.setStock(producto.getStock() - pc.getCantidad());
            repositorioProducto.save(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(pc.getCantidad());
            detalle.setPrecio_unitario(producto.getPrecio());
            detalle.setPedido(pedido);
            detalles.add(detalle);

            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(pc.getCantidad()));
            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        pedido.setDetallePedidos(detalles);

        repositorioPedido.save(pedido);

    }
}
