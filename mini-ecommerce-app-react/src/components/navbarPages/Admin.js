import { useEffect, useState } from 'react';
import { agregarProducto } from '../../service/ProductosService';
import { obtenerUsuarios } from '../../service/AuthService';
import './Admin.css';

export default function Admin() {
    const [usuarios, setUsuarios] = useState([]);
    const [loading, setLoading] = useState(false);
    const [formulario, setFormulario] = useState({
        nombre: "",
        descripcion: "",
        precio: "",
        stock: "",
        imagenUrl: "",
    });

    const mostrarUsuarios = async () => {
        setLoading(true);
        try {
            const respuesta = await obtenerUsuarios();
            console.log("respuesta de obtenerUsuarios:", respuesta);
            setUsuarios(respuesta);
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        mostrarUsuarios();
    }, []);

    const handleChange = (e) => {
        setFormulario({
            ...formulario,
            [e.target.name]: e.target.value,
        });
    };

    const formSubmit = async (e) => {
        e.preventDefault();
        try {
            await agregarProducto(formulario);
            setFormulario({
                nombre: "",
                descripcion: "",
                imagenUrl: "",
                precio: "",
                stock: ""
            });
            alert("Producto agregado correctamente");
        } catch (error) {
            alert("Error al agregar producto", error);
        }
    };

    return (
        <div className='admin-container'>
            <h2 className="admin-title">Panel de Administración</h2>
            <div className='admin-form-section'>
                <h3>Agregar Producto</h3>
                <form onSubmit={formSubmit} className='admin-form'>
                    <input type='text' placeholder='Nombre' value={formulario.nombre} name='nombre' onChange={handleChange} required />
                    <input type='text' placeholder='Descripción' value={formulario.descripcion} name='descripcion' onChange={handleChange} required />
                    <input type='text' placeholder='URL de Imagen' value={formulario.imagenUrl} onChange={handleChange} name='imagenUrl' required />
                    <input type='number' placeholder='Precio' value={formulario.precio} onChange={handleChange} name='precio' min="0" step="0.01" required />
                    <input type='number' placeholder='Stock' value={formulario.stock} onChange={handleChange} name='stock' min="0" required />
                    <button type='submit'>Agregar Producto</button>
                </form>
            </div>

            <div className='admin-table-section'>
                <h3>Usuarios Registrados</h3>
                {loading ? (
                    <div className="spinner"></div>
                ) : (
                    <table className="admin-table">
                        <thead>
                            <tr>
                                <th>Usuario</th>
                                <th>Email</th>
                                <th>Roles</th>
                            </tr>
                        </thead>
                        <tbody>
                            {usuarios.map(item => (
                                <tr key={item.id}>
                                    <td>{item.username}</td>
                                    <td>{item.email}</td>
                                    <td>{item.roles}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}