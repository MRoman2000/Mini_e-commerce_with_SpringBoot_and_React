package com.proyect.mini_ecommerce.repository;

import com.proyect.mini_ecommerce.modelo.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioListaDeseos extends JpaRepository<ListaDeseos, Integer> {
    Optional<ListaDeseos> findByUsuarioId(Integer usuarioId);
}
