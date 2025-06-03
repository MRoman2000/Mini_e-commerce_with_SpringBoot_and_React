import React, { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom'
import './Navbar.css'
export default function Navbar() {

    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));


    useEffect(() => {
        const onLogin = () => {
            setIsLoggedIn(!!localStorage.getItem('token'));
        };

        window.addEventListener('login', onLogin);

        return () => {
            window.removeEventListener('login', onLogin);
        };
    }, []);


    return (
        <div>
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
                    {isLoggedIn && (
                        <li>
                            <NavLink to="/usuario" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
                                Perfil de Usuario
                            </NavLink>
                        </li>
                    )}

                    {!isLoggedIn ? (
                        <li>
                            <NavLink to="/login" className="nav-link">Login</NavLink>
                        </li>
                    ) : (
                        <li>
                            <button
                                onClick={() => {
                                    localStorage.removeItem('token');
                                    setIsLoggedIn(false);
                                    window.location.href = '/login';
                                }}
                                className="nav-link"
                            >
                                Cerrar sesión
                            </button>
                        </li>
                    )}
                </ul>
            </nav>

        </div>
    )
}
