package com.proyect.mini_ecommerce.repository;

import com.proyect.mini_ecommerce.modelo.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCarrito extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuarioId(Integer usuarioId);


}
