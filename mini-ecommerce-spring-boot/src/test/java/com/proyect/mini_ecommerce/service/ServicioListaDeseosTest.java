package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.modelo.ListaDeseos;
import com.proyect.mini_ecommerce.modelo.Producto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioListaDeseos;
import com.proyect.mini_ecommerce.repository.RepositorioProducto;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServicioListaDeseosTest {

    @Mock
    private RepositorioListaDeseos repositorioListaDeseos;
    @Mock
    private RepositorioProducto repositorioProducto;
    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioListaDeseos servicioListaDeseos;

    @Test
    void agregarProductoAListaDeseos_deberiaAgregarProductoSiNoExiste() {
        Integer userId = 1;
        Integer productId = 2;

        Usuario usuario = new Usuario();
        usuario.setId(userId);

        Producto producto = new Producto();
        producto.setId(productId);

        ListaDeseos wishlist = new ListaDeseos();
        wishlist.setUsuario(usuario);

        when(repositorioListaDeseos.findByUsuarioId(userId)).thenReturn(Optional.of(wishlist));
        when(repositorioProducto.findById(productId)).thenReturn(Optional.of(producto));

        servicioListaDeseos.agregarProducto(userId, productId);

        assertTrue(wishlist.getProductos().contains(producto));
    }

    // Otros métodos de prueba...
}
