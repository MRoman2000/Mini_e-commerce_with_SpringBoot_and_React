import { createContext, useContext, useState, useEffect } from 'react';
import { obtenerCarrito, agregarProductoCarrito, eliminarCarrito, eliminarItemCarrito } from '../service/CarritoService';
import { useAuth } from './AuthContext';

const CartContext = createContext();

export function CartProvider({ children }) {
    const { token } = useAuth();
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        if (token) {
            obtenerCarrito(token)
                .then(data => {
                    const carritoFormateado = data.map(item => ({
                        id: item.id,
                        producto: {
                            id: item.productoId,
                            nombre: item.nombre,
                            descripcion: item.descripcion,
                            precio: item.precio,
                            imagenUrl: item.imagenUrl,
                        },
                        cantidad: item.cantidad
                    }));

                    setCartItems(carritoFormateado);
                })
                .catch(console.error);

        } else {
            setCartItems([]);
        }
    }, [token]);

    const addToCart = async (producto, cantidad = 1) => {
        if (!token) {
            alert("Debes iniciar sesión para agregar productos al carrito");
            return;
        }

        try {
            // Llamada backend para añadir producto y cantidad
            await agregarProductoCarrito(producto.id, cantidad, token);
            // Actualizar estado local sincronizando cantidades
            setCartItems(prev => {
                const existente = prev.find(item => item.producto.id === producto.id);
                if (existente) {
                    return prev.map(item =>
                        item.producto.id === producto.id
                            ? { ...item, cantidad: item.cantidad + cantidad }
                            : item
                    );
                }
                return [...prev, { producto, cantidad }];
            });

        } catch (error) {
            console.error("Error agregando producto al carrito backend", error);
            alert("No se pudo agregar el producto al carrito");
        }
    };

    const removeFromCart = async (itemId) => {
        try {
            await eliminarItemCarrito(itemId, token); // eliminas por itemId
            setCartItems(prev => prev.filter(item => item.id !== itemId)); // eliminas localmente por itemId
        } catch (error) {
            console.error("Error eliminando el producto del carrito", error);
        }
    };

    const clearCart = async () => {
        try {
            await eliminarCarrito(token);
            setCartItems([]);
        } catch (error) {
            console.error("Error eliminando el carrito", error);
        }
    };


    return (
        <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart }}>
            {children}
        </CartContext.Provider>
    );
}

export const useCart = () => useContext(CartContext);
