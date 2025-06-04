import axios from "axios";

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

export async function obtenerCarrito(token) {
  try {
    const response = await axios.get(API_URL, {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data; // aquí tu backend debe devolver el carrito con productos y cantidades
  } catch (error) {
    console.error('Error obteniendo carrito', error);
    throw error;
  }
}