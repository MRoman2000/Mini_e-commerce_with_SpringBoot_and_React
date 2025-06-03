package com.proyect.mini_ecommerce.repository;

import com.proyect.mini_ecommerce.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProducto  extends JpaRepository<Producto, Integer> {
}
