import axios from "axios";
import api from '../service/Api';

const API_URL = 'http://localhost:8080/api';

export const login = async (user) => {
    const response = await api.post('/auth/login', user);
    return response.data;
};

export const register = async (datos) => {
    const response = await axios.post(`${API_URL}/auth/register`, datos)
    return response.datos;
}

export const actualizarDatos = async (idUsuario, datosUsuario, token) => {
    const { data } = await api.put(`${API_URL}/usuarios/${idUsuario}`, datosUsuario);
    return data;
};

export const obtenerUsuarios = async () => {
    const respuesta = await api.get("/admin")
    return respuesta.data;
}

export const obtenerDatos = async () => {
    const response = await api.get("/usuarios/me");
    return response;
}

export const refreshToken = async () => {
    console.log("Consulta" + 1);
    const response = await api.post('/auth/refresh');
    localStorage.setItem('accessToken', response.data.accessToken);
    return response.data;
};


