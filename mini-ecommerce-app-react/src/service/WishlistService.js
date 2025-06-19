import api from "./Api";


export const agregarProductoWishlist = async (productoId) => {
    await api.post(`/wishlist/agregar/${productoId}`);
}


export const mostrarListaDeseos = async () => {
    const response = await api.get("/wishlist");
    return response;
}

export const eliminarProductoDeLista = async(productoId) => {
    const response  = await api.delete(`/wishlist/${productoId}`)
}
