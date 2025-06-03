package com.proyect.mini_ecommerce.auth;

public class AuthResponse {
    private  String tokem;

    public AuthResponse(String tokem) {
        this.tokem = tokem;
    }

    public String getTokem() {
        return tokem;
    }

    public void setTokem(String tokem) {
        this.tokem = tokem;
    }
}
