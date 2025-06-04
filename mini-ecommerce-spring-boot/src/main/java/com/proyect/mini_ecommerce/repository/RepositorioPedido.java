package com.proyect.mini_ecommerce.repository;

import com.proyect.mini_ecommerce.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Integer> {
}
