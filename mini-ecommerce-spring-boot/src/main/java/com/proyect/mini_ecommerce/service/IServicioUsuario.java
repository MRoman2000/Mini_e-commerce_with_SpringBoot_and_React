package com.proyect.mini_ecommerce.service;

import com.proyect.mini_ecommerce.dto.UserDto;
import com.proyect.mini_ecommerce.modelo.Usuario;

import java.util.List;

public interface IServicioUsuario {

    public List<UserDto> listarUsuario();

    public UserDto crearUsuario(Usuario usuario);

    public Usuario buscarUsuarioPorId(Integer id);

    public void eliminarUsuario(Integer id);

    public Usuario actualizarUsuario(Integer id, Usuario usuario);


}
