import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext'; // donde tengas el token
import './Cesta.css';
import { crearPedido } from '../service/PedidoService';
import { obtenerCarrito, agregarProductoCarrito } from '../service/CarritoService'; // importa tus funciones

export default function Cesta() {
    const { cartItems, removeFromCart, clearCart } = useCart();
    const { token } = useAuth(); // o donde almacenes el JWT

    const calcularTotal = () =>
        cartItems.reduce(
            (acc, item) => acc + item.producto.precio * item.cantidad, 0
        );

    const handleCrearPedido = async () => {
        try {
            const productosParaEnviar = cartItems.map(item => ({
                productoId: item.producto.id,
                cantidad: item.cantidad
            }));

            await crearPedido(productosParaEnviar, token);

            alert('Pedido enviado correctamente');
            clearCart();
        } catch (error) {
            console.error('Error al enviar pedido', error);
            alert('Hubo un problema al enviar el pedido');
        }
    };

    return (
        <div className="carrito-container">
            <h2 className="carrito-titulo">🛒 Tu Carrito</h2>

            {cartItems.length === 0 ? (
                <p className="carrito-vacio">Tu carrito está vacío.</p>
            ) : (
                <div className="carrito-listado">
                    {cartItems.map(item => (
                        <div key={item.producto.id} className="carrito-item">
                            <img
                                src={item.producto.imagenUrl}
                                alt={item.producto.nombre}
                                className="carrito-imagen"
                            />
                            <div className="carrito-detalles">
                                <h3>{item.producto.nombre}</h3>
                                <p>{item.producto.descripcion}</p>
                                <p>
                                    {item.cantidad} x {item.producto.precio.toFixed(2)} €
                                </p>

                            </div>
                            <div className='container-btn-eliminar'>
                                <button
                                    className="btn-eliminar"
                                    onClick={() => removeFromCart(item.producto.id)}
                                >
                                    Eliminar
                                </button>
                            </div>


                        </div>

                    ))}
                    <div className="carrito-total">
                        <h3>Total: {calcularTotal().toFixed(2)} €</h3>
                        <button className="btn-vaciar" onClick={clearCart}>
                            Vaciar carrito
                        </button>
                        <div>
                            <button className="btn-confirmar" onClick={handleCrearPedido} >
                                Confirmar el pedido
                            </button>
                        </div>
                    </div>

                </div>

            )}

        </div>
    );
}
