import api from './Api';

const API_URL = 'http://localhost:8080/api/pedidos';

export async function crearPedido(productos) {
    const response = await api.post(API_URL, productos);
    return response.data;
}

export async function obtenerPedidos() {
    const response = await api.get(API_URL);
    return response.data;
}
