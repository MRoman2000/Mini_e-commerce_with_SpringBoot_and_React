import axios from 'axios';

const API_URL = 'http://localhost:8080/api/pedidos';


export async function crearPedido(productos, token) {
    const response = await axios.post(
        API_URL, productos, {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    });
    return response.data;
}
