import React from 'react'
import './Home.css'
import { useNavigate } from 'react-router-dom';

export default function Home() {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <h1>¡Bienvenido a Mini E-commerce!</h1>
      <p>Descubre productos increíbles y gestiona tus pedidos fácilmente. Tu tienda online favorita a un solo clic.</p>
      <button className="btn-explore" onClick={() => navigate('/productos')}>
        Explorar Productos
      </button>
    </div>
  );
}