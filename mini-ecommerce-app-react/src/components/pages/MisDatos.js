import { useAuth } from './../../context/AuthContext';

export default function MisDatos() {
    const { user, logout } = useAuth();
    if (!user) return <p>Cargando información del usuario...</p>;

    return (
        <div className="usuario-info">
            {user ? (
                <>
                        <p><strong>Username:</strong> {user.username}</p>
                        <p><strong>Email:</strong> {user.email}</p>
                        <p><strong>Rol:</strong> {user.rol}</p>
                        <button onClick={logout} className="btn-logout">Cerrar sesión</button>
                </>
            ) : (
                <p>Cargando información del usuario...</p>
            )}
        </div>
    );
}
