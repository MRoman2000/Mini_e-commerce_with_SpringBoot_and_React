import axios from "axios";

const API_URL = 'http://localhost:8080/api/productos';


export const getProductos = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("Error al obtener productos:", error);
    throw error;  // para que React Query o quien llame sepa que hubo error
  }
};

export const buscarProducto = async (nombre) => {
  const respuesta = await axios.get(`${API_URL}/buscar`, {
    params: { nombre }
  });
  return respuesta.data;
}


export const agregarProducto = async (formulario, token) => {
  const respuesta = await axios.post(`${API_URL}/agregar`, formulario, {
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  })
  return respuesta;
}