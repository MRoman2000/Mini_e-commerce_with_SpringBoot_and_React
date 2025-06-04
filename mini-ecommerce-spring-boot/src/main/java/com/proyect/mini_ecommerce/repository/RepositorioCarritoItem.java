package com.proyect.mini_ecommerce.repository;

import com.proyect.mini_ecommerce.modelo.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCarritoItem extends JpaRepository<CarritoItem,Integer> {
    List<CarritoItem> findByCarritoUsuarioId(Integer usuarioId);

}
