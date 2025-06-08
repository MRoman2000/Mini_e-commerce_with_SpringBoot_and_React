import axios from "axios";

const API_URL = 'http://localhost:8080/api/auth';


export const login = async (user) => {
  const response = await axios.post(`${API_URL}/login`, user);
  return response.data; // devolver los datos
};



export const logout = () => {
  localStorage.removeItem('token');
  window.dispatchEvent(new Event('logout'));
};


export const register = async (datos) => {
    const response = await axios.post(`${API_URL}/register`,datos)
    return response.datos;
}
