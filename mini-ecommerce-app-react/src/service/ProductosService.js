import axios from "axios";

const BASE_URL = 'http://localhost:8080/api/productos';


export const getProductos = async () => {
    const response = await axios.get(BASE_URL);
    return response.data;
}
