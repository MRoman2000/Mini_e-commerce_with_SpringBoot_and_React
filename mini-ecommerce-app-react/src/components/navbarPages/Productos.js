import { useState } from 'react'
import { getProductos } from '../../service/ProductosService';
import { useCart } from '../../context/CartContext';
import { useQuery } from '@tanstack/react-query';
import { buscarProducto } from '../../service/ProductosService';
import './Productos.css';

export default function Productos() {
    //  const [productos, setProductos] = useState([]);
    const { addToCart } = useCart();
    const [query, setQuery] = useState("");
    const [searchResults, setSearchResults] = useState(null);
    /*   useEffect(() => {
           console.log('Componente Productos montado y ejecutando fetch');
           obtenerProductos();
       }, []); */

    // Aquí React Query maneja el fetching, caché, estado y errores
    const { data: productos, error, isLoading } = useQuery({
        queryKey: ['productos'],
        queryFn: getProductos,
    });

    const handleSearch = async (e) => {
        try {
            if (query.trim() === "") {
                setSearchResults(null);
                return;
            }
            const resultado = await buscarProducto(query);
            setSearchResults(resultado);
        } catch (error) {
            console.error('Error buscando producto:',error.message);
        }
    }


    if (isLoading) return <p>Cargando productos...</p>;
    if (error) return <p>Error al cargar productos</p>;
    /*  const obtenerProductos = async () => {
          try {
              const productos = await getProductos();
              setProductos(productos);
              console.log("Productos obtenidos:", productos);
          } catch (error) {
              console.error("Error al obtener los productos:", error);
          }
      } */

    const productosAMostrar = searchResults !== null ? searchResults : productos;
    return (
        <div className='container'>
            <h1>Lista de Productos</h1>

            <div className='header'>
                <input
                    type='text'
                    placeholder='Buscar productos...'
                    className='search-input'
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                />
                <button className='search-button' onClick={handleSearch}>Buscar</button>
            </div>

            <div className="productos-header">
                {productosAMostrar.length > 0 ? (
                    <ul className='productos-list'>
                        {productosAMostrar.map(producto => (
                            <li key={producto.id} className='producto-item'>
                                <img className="product-image" src={producto.imagenUrl} alt={producto.nombre} />
                                <h2 className="product-name">{producto.nombre}</h2>
                                <p className="product-description">{producto.descripcion}</p>
                                <p className="product-price"> $ {producto.precio}</p>
                                <button className="btn-add-cart" onClick={() => addToCart(producto)}>
                                    Añadir al carrito
                                </button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    searchResults !== null ? (
                        <p>No se encontró ningún producto con el nombre: '{query}'</p>
                    ) : (
                        <p>No hay productos disponibles.</p>
                    )
                )}
            </div>
        </div>
    )
}
