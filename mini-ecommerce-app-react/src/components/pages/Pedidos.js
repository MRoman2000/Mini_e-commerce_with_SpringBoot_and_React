import { useEffect, useState } from 'react'
import { obtenerPedidos } from '../../service/PedidoService'
import { useAuth } from './../../context/AuthContext';
import './Pedidos.css'

export default function Pedidos() {
  
  const { token } = useAuth();
  const [pedidos, setPedidos] = useState([]);

  useEffect(() => {
    obtenerPedidosPorCliente();
  }, []);

  const obtenerPedidosPorCliente = async () => {
    try {
      const response = await obtenerPedidos(token);
      setPedidos(response);
      console.log(response)
    } catch (error) {
      console.error("ERROR al obtener pedidos:", error);
    }
  };

  return (
    <div className="pedidos-container">
      <h2>Mis Pedidos</h2>

      {pedidos.length === 0 ? (
        <p className="no-pedidos">No tienes pedidos todavía.</p>
      ) : (
        pedidos.map((pedido) => (
          <div key={pedido.id} className="pedido-card">
            <div className="pedido-info">
              <p><strong>Fecha:</strong> {new Date(pedido.fecha).toLocaleString()}</p>

            </div>

            <div>

              <ul className="detalles-list">
                {pedido.detalles.map((detalle, index) => (
                  <li key={index} className="detalle-item">
                    <img className="detalle-image" src={detalle.imagenUrl} alt={detalle.nombreProducto} />
                    <div className="detalle-info">
                      <p className="detalle-nombre">{detalle.nombreProducto}</p>
                      <p className="detalle-precio">
                        {detalle.cantidad} × {detalle.precioUnitario.toFixed(2)} €
                      </p>
                    </div>
                  </li>
                ))}
              </ul>
            </div>
            <p><strong>Total:</strong> {pedido.total.toFixed(2)} €</p>
          </div>
        ))
      )}
    </div>
  );
}