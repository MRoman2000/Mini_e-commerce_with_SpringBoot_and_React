import { useEffect, useState } from 'react'
import { getProductos } from '../../service/ProductosService';
import { useCart } from '../../context/CartContext';
import { useQuery } from '@tanstack/react-query';
import { buscarProducto } from '../../service/ProductosService';
import { FaHeart } from 'react-icons/fa';
import './Productos.css';
import { useSearchParams } from 'react-router-dom';

export default function Productos() {
    const { addToCart, addTowishlist } = useCart();
    const [searchParams, setSearchParams] = useSearchParams();
    const [query, setQuery] = useState(searchParams.get('search') || '');
    const [searchResults, setSearchResults] = useState(null);

    const { data: productos, error, isLoading } = useQuery({
        queryKey: ['productos'],
        queryFn: getProductos,
        staleTime: 1000 * 60 * 5,
        refetchOnWindowFocus: false,
    });

    // Búsqueda que se ejecuta solo cuando pulsas el botón o Enter
    const handleSearch = async () => {
        if (query.trim() === '') {
            setSearchResults(null);
            setSearchParams({});
            return;
        }
        setSearchParams({ search: query });
        try {
            const resultado = await buscarProducto(query);
            setSearchResults(resultado);
        } catch (error) {
            console.error('Error buscando producto:', error.message);
        }
    };

    // Cuando el componente monta, si hay query en la URL, hace la búsqueda inicial
    useEffect(() => {
        const q = searchParams.get('search');
        if (q && q.trim() !== '') {
            setQuery(q);
            (async () => {
                try {
                    const resultado = await buscarProducto(q);
                    setSearchResults(resultado);
                } catch (error) {
                    console.error('Error buscando producto:', error.message);
                }
            })();
        } else {
            setSearchResults(null);
            setQuery('');
        }
    }, [searchParams]);

    if (isLoading) return <p>Cargando productos...</p>;
    if (error) return <p>Error al cargar productos</p>;

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
                    onKeyDown={(e) => { if (e.key === 'Enter') handleSearch(); }}
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
                                <div className="acciones-producto">
                                    <button
                                        className="btn-add-cart"
                                        onClick={() => addToCart(producto)}
                                        disabled={producto.stock === 0}
                                    >
                                        {producto.stock === 0 ? 'Sin stock' : 'Añadir al carrito'}
                                    </button>
                                    <button className="btn-wishlist" onClick={() => addTowishlist(producto)}>
                                        <FaHeart size={20} className="icono-corazon" />
                                    </button>
                                </div>
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
    );
}