import React, { useEffect, useState } from 'react'
import axios from "axios";
import './Usuario.css'; // Asegúrate de tener un archivo CSS para estilos
export default function Usuario() {
    const token = localStorage.getItem('token');
    const [user, setUser] = useState(() => {
        const saved = localStorage.getItem('user');
        return saved ? JSON.parse(saved) : null;
    });

    useEffect(() => {
        if (!user && token) {
            const fetchUserData = async () => {
                try {
                    const response = await axios.get('http://localhost:8080/api/usuarios/me', {
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    });
                    setUser(response.data);
                    localStorage.setItem('user', JSON.stringify(response.data)); // guardamos
                } catch (error) {
                    console.error("Error al obtener los datos del usuario:", error);
                }
            };

            fetchUserData();
        }
    }, [token, user]);

    return (
        <div className='usuario-container'>
            <h1>Perfil de Usuario</h1>
            {user ? (
                <div className='usuario-info'>
                    <p><strong>Username:</strong> {user.username}</p>
                    <p><strong>Correo Electrónico:</strong> {user.email}</p>
                    <p><strong>Rol:</strong> {user.rol}</p>
                </div>
            ) : (
                <p>Cargando información del usuario...</p>
            )}
        </div>
    );
}
