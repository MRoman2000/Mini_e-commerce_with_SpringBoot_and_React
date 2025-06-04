// src/context/CartContext.js
import { createContext, useContext, useState } from 'react';

const CartContext = createContext();

export function CartProvider({ children }) {
    const [cartItems, setCartItems] = useState([]);

    const addToCart = (producto, cantidad = 1) => {
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
    };

    const removeFromCart = (productoId) => {
        setCartItems(prev => prev.filter(item => item.producto.id !== productoId));
    };

    const clearCart = () => setCartItems([]);

    return (
        <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart }}>
            {children}
        </CartContext.Provider>
    );
}

export const useCart = () => useContext(CartContext);
