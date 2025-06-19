
import api from "./Api";


const API_URL = 'http://localhost:8080/api/carrito';


export async function agregarProductoCarrito(productoId, cantidad) {
  try {
    const response = await api.post(API_URL,
      { productoId, cantidad },
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
    console.log(response)
    return response.data;
  } catch (error) {
    console.error('Error obteniendo carrito', error);
    throw error;
  }
}


export async function eliminarCarrito() {
  try {
    const response = await api.delete("/carrito");
    return response.data;
  } catch (error) {
    console.error('Error eliminando carrito:', error);
    throw error;
  }
}

export async function eliminarItemCarrito(productoId) {
  await api.delete(`${API_URL}/item/${productoId}`, {
  });
}
