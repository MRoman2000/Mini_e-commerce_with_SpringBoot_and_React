import { useState } from 'react';
import { useAuth } from './../../context/AuthContext';
import './MisDatos.css'
export default function MisDatos() {
    const { user, logout } = useAuth();
    const [formulario, setFormulario] = useState({
        username: "",
        email: "",
        password: "",
        confirmarPassword: ""
    });
    const [error, setError] = useState('');

    if (!user) return <p>Cargando información del usuario...</p>;


    const formSubmit = (e) => {
        e.preventDefault();
        if (formulario.password !== formulario.confirmarPassword) {
            setError('Las contraseñas no coinciden');
            return;
        }

        setError("");


    }

    const handleChange = (e) => {
        setFormulario({
            ...formulario,
            [e.target.name]: e.target.value,
        });
    };


    return (
        <div className='conteiner-usuario'>
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

            <div className="usuario-editar">
                <form onSubmit={formSubmit} className="form-editar">
                    <h2>Editar Perfil</h2>

                    <input type="text" placeholder="Nombre de usuario" name="username" onChange={handleChange} value={formulario.username} />
                    <input type="email" placeholder="Correo electrónico" name="email" onChange={handleChange} value={formulario.email} />
                    <input type="password" placeholder="Nueva contraseña" name="password" onChange={handleChange} value={formulario.password} />
                    <input type="password" placeholder="Confirmar contraseña" name="confirmarPassword" onChange={handleChange} value={formulario.confirmarPassword} />

                    <button type="submit">Guardar Cambios</button>

                    {error && <p className="error">{error}</p>}
                </form>
            </div>

        </div >
    );
}
