package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.Usuario;
import com.proyect.mini_ecommerce.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        return new UserDto(guardado.getUsername(), guardado.getEmail(), guardado.getRol());
    }


    public UserDto findByUsername(String username) {
        Usuario user = repositorioUsuario.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
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
    public UserDto actualizarUsuario(Integer id, Usuario usuarioNuevo) {
        Usuario usuarioExistente = repositorioUsuario.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setUsername(usuarioNuevo.getUsername());

        if (usuarioNuevo.getPassword() != null && !usuarioNuevo.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(usuarioNuevo.getPassword());
            usuarioExistente.setPassword(encodedPassword);
        }

        Usuario usuarioActualizado = repositorioUsuario.save(usuarioExistente);

        // Devuelve un DTO construido a partir del usuario actualizado
        UserDto dto = new UserDto();
        dto.setUsername(usuarioActualizado.getUsername());
        dto.setEmail((usuarioActualizado.getEmail()));
        // No devuelvas la contraseña en el DTO por seguridad

        return dto;
    }
}
