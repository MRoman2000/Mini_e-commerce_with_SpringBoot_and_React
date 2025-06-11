package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario implements IServicioUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listarUsuario() {
        return repositorioUsuario.findAll();
    }

    @Override
    public UserDto crearUsuario(Usuario usuario) {
        String username = usuario.getUsername();
        Optional<Usuario> existente = repositorioUsuario.findByUsername(username);

        if (existente.isPresent()) {
            return null;
        }
        String rawPassword = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(rawPassword));
        Usuario guardado = repositorioUsuario.save(usuario);
        return new UserDto(guardado.getId(), guardado.getUsername(), guardado.getEmail());
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer id) {
        return repositorioUsuario.findById(id).orElse(null);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        repositorioUsuario.deleteById(id);
    }


    @Override
    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizar) {

        Usuario usuarioExistente = repositorioUsuario.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuarioActualizar.getUsername() != null && !usuarioActualizar.getUsername().isEmpty()) {
            usuarioExistente.setUsername(usuarioActualizar.getUsername());
        }

        if (usuarioActualizar.getEmail() != null && !usuarioActualizar.getEmail().isEmpty()) {
            usuarioExistente.setEmail(usuarioActualizar.getEmail());
        }

        if (usuarioActualizar.getPassword() != null && !usuarioActualizar.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(usuarioActualizar.getPassword());
            usuarioExistente.setPassword(encodedPassword);
        }

        return repositorioUsuario.save(usuarioExistente);

    }


}
