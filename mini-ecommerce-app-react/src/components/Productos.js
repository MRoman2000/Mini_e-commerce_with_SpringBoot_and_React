import React, { useEffect, useState } from 'react'
import { getProductos } from '../service/ProductosService';
import './Productos.css';
export default function Productos() {
    const [productos, setProductos] = useState([]);

    useEffect(() => {
        obtenerProductos();
    }, []);

    const obtenerProductos = async () => {
        try {
            const productos = await getProductos();
            setProductos(productos);
            console.log("Productos obtenidos:", productos);
        } catch (error) {
            console.error("Error al obtener los productos:", error);
        }
    }
    return (
        <div className='container'>
            <h1>Lista de Productos</h1>
            
            <div className='header'>
                <input type='text' placeholder='Buscar productos...' className='search-input' />
                <button className='search-button'>Buscar</button>
            </div>

            <div className='productos-header'>
                {productos.length > 0 ? (
                    <ul className='productos-list'>
                        {productos.map(producto => (
                            <li key={producto.id} className='producto-item'>
                                <h2>{producto.nombre}</h2>
                                <p>Precio: ${producto.precio}</p>
                                <p>Descripción: {producto.descripcion}</p>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No hay productos disponibles.</p>
                )}
            </div>
        </div>
    )
}
