package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Usuario crearUsuario(Usuario usuario) {
        String rawPassword = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(rawPassword));
        return repositorioUsuario.save(usuario);
    }

    public UserDto findByUsername(String username) {
        Usuario user = repositorioUsuario.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Convertir entidad User a DTO (solo datos que quieres exponer)
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRol(user.getRol());
        return userDto;
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
    public Usuario actualizarUsuario(Integer id, Usuario usuarioNuevo) {

        Usuario usuario = repositorioUsuario.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado "));
        usuario.setUsername(usuarioNuevo.getUsername());
        return repositorioUsuario.save(usuario);

    }
}
