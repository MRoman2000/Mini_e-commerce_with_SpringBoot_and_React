import React, { useEffect, useState } from 'react'
import { mostrarListaDeseos } from '../../service/WishlistService';
import { eliminarProductoDeLista } from '../../service/WishlistService';
import { FaTrash } from 'react-icons/fa'
export default function ListaDeseos() {
  const [ListaDeseos, setListaDeseos] = useState([]);


  const mostrarLista = async () => {
    const response = await mostrarListaDeseos();
    setListaDeseos(response.data);
    console.log(response);
  }

  const eliminarProducto = async (productoId) => {
    const response = await eliminarProductoDeLista(productoId);
    console.log(response);
    mostrarLista();
  }

  useEffect(() => {
    mostrarLista();
  }, []);

  return (
    <div className='container'>
      <h1>Lista de Deseos</h1>
      {ListaDeseos.length === 0 ? (
        <p>No tienes productos en la lista</p>
      ) : (
        <ul className='productos-list'>
          {ListaDeseos.map(producto => (
            <li key={producto.id} className='producto-item'>
              <img className="product-image" src={producto.imagenUrl} alt={producto.nombre} />
              <h2 className="product-name">{producto.nombre}</h2>
              <p className="product-description">{producto.descripcion}</p>
              <p className="product-price"> $ {producto.precio}</p>
              <button className='btn-delete' onClick={() => eliminarProducto(producto.id)}>
                <FaTrash size={18} className="icono-papelera" />
              </button>
            </li>
          ))}
        </ul>
      )}

    </div>
  )
}
