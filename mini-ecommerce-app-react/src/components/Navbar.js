import { NavLink } from 'react-router-dom'
import './Navbar.css'
import { useAuth } from '../context/AuthContext';

export default function Navbar() {
    const { user } = useAuth();

    return (
      
            <nav className="nav-bar">
                <ul className="nav-list">
                    <li>
                        <NavLink to="/" end className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
                            Inicio
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/productos" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
                            Productos
                        </NavLink>

                    </li>

                    {user && (
                        <>
                            <li>
                                <NavLink
                                    to="/usuario"
                                    className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}
                                >
                                    Perfil de Usuario
                                </NavLink>
                            </li>
                            <li>
                                <NavLink
                                    to="/cesta"
                                    className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}
                                >
                                    Cesta
                                </NavLink>
                            </li>
                        </>
                    )}
                    {!user && (
                        <li><NavLink to="/login" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>Login</NavLink></li>
                    )}

                </ul>
            </nav>

    )
}
