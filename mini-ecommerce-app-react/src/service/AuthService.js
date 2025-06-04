import axios from "axios";

const BASE_URL = 'http://localhost:8080/auth/login';


export const login = async (user) => {
  const response = await axios.post(BASE_URL, user);
  return response.data; // devolver los datos
};



export const logout = () => {
  localStorage.removeItem('token');
  window.dispatchEvent(new Event('logout')); // Notificar a otros componentes
};
