import axios from "axios";

const API_URL = 'http://localhost:8080/api';

export const login = async (user) => {
  const response = await axios.post(`${API_URL}/auth/login`, user);
  return response.data; // devolver los datos
};

export const register = async (datos) => {
  const response = await axios.post(`${API_URL}/auth/register`, datos)
  return response.datos;
}

export const actualizarDatos = async (idUsuario, datosUsuario, token) => {
    const { data } = await axios.put(`${API_URL}/usuarios/${idUsuario}`, datosUsuario, {
        headers: { Authorization: `Bearer ${token}` }
    });
     if (data.token) {
        localStorage.setItem('token', data.token);
    }
    return data;
};


export const obtenerDatos = async (token) => {
  const response = await axios.get(`${API_URL}/usuarios/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response;
}




