import './Usuario.css'; // Asegúrate de tener un archivo CSS para estilos
import { Link, Outlet } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

export default function Usuario() {
    const token = localStorage.getItem('accessToken');
    let userRoles = [];

    if (token) {
        const decoded = jwtDecode(token);
        userRoles = decoded.roles ? [decoded.roles] : [];
    }
    return (
        <div className="container-principal">
            <h1>Perfil de Usuario</h1>
            <div className="layout-usuario">
                <aside className="sidebar-usuario">
                    <ul className="lista-lateral">
                        <Link to="mis-datos"><li>Mis Datos</li></Link>
                        <Link to="pedidos"><li>Pedidos</li></Link>
                        <Link to="lista-deseos"><li>Lista de deseos</li></Link>

                        {userRoles.includes('ROLE_ADMIN') && (
                            <Link to="admin">
                                <li>Admin Page</li>
                            </Link>
                        )}
                    </ul>
                </aside>
                <main className="contenido-usuario">
                    <Outlet />
                </main>
            </div>
        </div>
    );
}
