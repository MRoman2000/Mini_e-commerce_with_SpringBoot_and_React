import React, { useState } from 'react'
import { agregarProducto } from '../../service/ProductosService';
import { useAuth } from '../../context/AuthContext';
import './Admin.css';


export default function Admin() {

    const { token } = useAuth();
    const [formulario, setFormulario] = useState({
        nombre: "",
        descripcion: "",
        imagen_url: "",
        precio: "",
        stock: ""
    })

    const handleChange = (e) => {
        setFormulario({
            ...formulario, [e.target.name]: e.target.value,
        })
    }

    const formSubmit = async (e) => {
        e.preventDefault();
        try {
            const respuesta = await agregarProducto(formulario, token);
            alert(respuesta);
        } catch (error) {
            console.error("error", error);
        }
    }

    return (

        <div className='container'>
            <h2>Agregar Producto</h2>
            <div className='container-form'>
                <form onSubmit={formSubmit} className='form-editar'>
                    <input type='text' placeholder='Nombre' value={formulario.nombre} name='nombre' onChange={handleChange} />
                    <input type='text' placeholder='Descripcion' value={formulario.descripcion} name='descripcion' onChange={handleChange} />
                    <input type='text' placeholder='Imagen_url' value={formulario.imagen_url} onChange={handleChange} name='imagen_url' />
                    <input type='number' placeholder='Precio' value={formulario.precio} onChange={handleChange} name='precio' />
                    <input type='number' placeholder='Stock' value={formulario.stock} onChange={handleChange} name='stock' />
                    <button type='submit'>Agregar Producto</button>
                </form>
            </div>
        </div>
    )
}
