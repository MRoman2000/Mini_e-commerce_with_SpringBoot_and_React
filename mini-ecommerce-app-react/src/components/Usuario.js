import './Usuario.css'; // Asegúrate de tener un archivo CSS para estilos

import { useAuth } from '../context/AuthContext';

export default function Usuario() {
    const { user, logout } = useAuth();

    return (
        <div className="usuario-container">
            <h1>Perfil de Usuario</h1>
            {user ? (
                <div className="usuario-info">
                    <p><strong>Username:</strong> {user.username}</p>
                    <p><strong>Email:</strong> {user.email}</p>
                    <p><strong>Rol:</strong> {user.rol}</p>

                    <button onClick={logout} className="btn-logout">
                        Cerrar sesión
                    </button>
                </div>
            ) : (
                <p>Cargando información del usuario...</p>
            )}
        </div>
    );
}