package com.proyect.mini_ecommerce.repository;

import com.proyect.mini_ecommerce.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
