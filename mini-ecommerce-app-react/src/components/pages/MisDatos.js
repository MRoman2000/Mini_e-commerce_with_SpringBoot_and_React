import { useState } from 'react';
import { useAuth } from './../../context/AuthContext';
import { actualizarDatos } from '../../service/AuthService';
import './MisDatos.css'


export default function MisDatos() {
    const { user, logout, loading, token } = useAuth();
    const [formulario, setFormulario] = useState({
        username: "",
        email: "",
        password: "",
        confirmarPassword: ""
    });
    const [error, setError] = useState('');


    const updateDatos = async () => {
        try {

            const resultado = await actualizarDatos(user.id, formulario, token)
            setFormulario(resultado);
            limpiarFormulario();
        } catch (error) {
            console.error("error", error);
        }
    }

    const limpiarFormulario = () => {
        setFormulario({
            username: "",
            email: "",
            password: "",
            confirmarPassword: "",

        })

    }


    const formSubmit = (e) => {
        e.preventDefault();
        if (formulario.password !== formulario.confirmarPassword) {
            setError('Las contraseñas no coinciden');
            return;
        }
        updateDatos();
        setError("");
    }

    const handleChange = (e) => {
        setFormulario({
            ...formulario,
            [e.target.name]: e.target.value,
        });
    };
    if (loading) return <p>Cargando información del usuario...</p>;
    if (!user) return <p>No hay usuario logueado.</p>;

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
