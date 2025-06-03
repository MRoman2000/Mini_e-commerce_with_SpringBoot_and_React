package com.proyect.mini_ecommerce.dto;

public class UserDto {
    private String username;
    private String email;
    private String rol;

    public UserDto(String username, String email, String rol) {
        this.username = username;
        this.email = email;
        this.rol = rol;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
