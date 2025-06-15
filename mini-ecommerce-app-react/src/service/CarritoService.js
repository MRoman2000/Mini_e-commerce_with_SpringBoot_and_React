import axios from "axios";
import api from "./Api";


const API_URL = 'http://localhost:8080/api/carrito';


export async function agregarProductoCarrito(productoId, cantidad, token) {
  try {
    const response = await axios.post(API_URL,
      { productoId, cantidad },
      { headers: { Authorization: `Bearer ${token}` } }
    );
    return response.data;
  } catch (error) {
    console.error('Error agregando producto al carrito', error);
    throw error;
  }
}

export async function obtenerCarrito() {
  try {
    const response = await api.get('/carrito'); 
    return response.data;
  } catch (error) {
    console.error('Error obteniendo carrito', error);
    throw error;
  }
}


export async function eliminarCarrito(token) {
  try {
    const response = await axios.delete(API_URL, {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  } catch (error) {
    console.error('Error eliminando carrito:', error);
    throw error;
  }
}

export async function eliminarItemCarrito(productoId, token) {
  await axios.delete(`${API_URL}/item/${productoId}`, {
    headers: { Authorization: `Bearer ${token}` }
  });
}
