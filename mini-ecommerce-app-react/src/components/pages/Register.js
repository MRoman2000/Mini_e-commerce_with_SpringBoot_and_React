import { useState } from 'react'
import './Registes.css';
import { register } from '../../service/AuthService';
import { useNavigate } from 'react-router-dom';

export default function Register() {
    const [formulario, setFormulario] = useState({
        username: "",
        password: "",
        email: "",
    });
    const navigate = useNavigate();
    const [verPassword, setVerPassword] = useState(false);

    const togglePassword = () => {
        setVerPassword(prev => !prev);
    };

    const formSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await register(formulario);
            alert("✅ Usuario creado con éxito");
            limpiarFormulario();
        } catch (error) {
            console.error("Error:", error);
            if (error.response) {
                const mensaje = error.response.data.message || "Error desconocido";

                if (error.response.status === 400) {
                    alert("⚠️ " + mensaje);
                } else {
                    alert("❌ Error del servidor: " + mensaje);
                }
            } else {
                alert("❌ No se pudo conectar al servidor");
            }
        }
    };


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormulario({
            ...formulario, [name]: value
        });
    }

    const limpiarFormulario = () => {
        setFormulario({
            username: '',
            password: '',
            email: ''
        });
    };


    return (

        <form className="registro-form" onSubmit={formSubmit}>
            <h2>Crear Cuenta</h2>

            <input
                type="text"
                placeholder="Nombre de usuario"
                name="username"
                required
                value={formulario.username}
                onChange={handleChange}
            />

            <input
                type="email"
                placeholder="Correo electrónico"
                name="email"
                value={formulario.email}
                onChange={handleChange}
            />

            <div className="input-password-container">
                <input
                    type={verPassword ? 'text' : 'password'}
                    placeholder="Contraseña"
                    name="password"
                    required
                    value={formulario.password}
                    onChange={handleChange}
                />
                <span onClick={togglePassword} className="ver-password-icon">
                    {verPassword ? '🙈' : '👁️'}
                </span>
            </div>

            <div className="botones-container">
                <button type="submit" className="btn-registrarse">Registrarse</button>
                <button type="button" className="btn-login" onClick={() => navigate("/login")}>Login</button>
            </div>

        </form>

    );
};

