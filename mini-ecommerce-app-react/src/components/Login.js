import React, { useState } from 'react'
import './Login.css'

import { login } from '../service/AuthService';
import { useNavigate } from 'react-router-dom';
export default function Login() {

    const [errorMessage, setErrorMessage] = useState('');
    const [user, setUser] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate();

    const formSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await login(user);

            console.log('Respuesta del login:', response); // Muestra la respuesta completa

            if (response.token) {
                console.log('Token recibido:', response.token); // Muestra el token
                localStorage.setItem('token', response.token);
                window.dispatchEvent(new Event('login'));
                navigate('/usuario');
            }

        } catch (error) {
            console.error('Error en el login:', error);
            setErrorMessage('Usuario o contraseña incorrectos');
        }

    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser({
            ...user, [name]: value
        });
    }

    return (
        <div className="login-container">
            <h2 className="login-title">🚀 Iniciar Sesión</h2>
            <form onSubmit={formSubmit} className="login-form">
                <div className="form-group">
                    <label htmlFor="username">Usuario:</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        required
                        value={user.username}
                        onChange={handleChange}
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="password">Contraseña:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        required
                        value={user.password}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
                <div className="form-button">
                    <button className="btn-login" type="submit">Iniciar Sesión</button>
                    <button className="btn-register" type="button" onClick={() => navigate("/register")}>
                        Registrarse
                    </button>
                </div>

            </form>
        </div>

    )
}
