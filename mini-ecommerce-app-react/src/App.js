import { Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Productos from './components/Productos';
import Login from './components/Login';
import Usuario from './components/Usuario';
import ProtectedRoute from './components/ProtectedRoute';
import Cesta from './components/Cesta';
import Pedidos from './components/pages/Pedidos';
import ListaDeseos from './components/pages/ListaDeseos';
import MisDatos from './components/pages/MisDatos';
import { Navigate } from "react-router-dom";
function App() {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/productos" element={<Productos />} />
        <Route path="/login" element={<Login />} />
        <Route path='/cesta' element={<Cesta />} />


        <Route
          path="/usuario"
          element={
            <ProtectedRoute>
              <Usuario />
            </ProtectedRoute>
          }
        >

          {/* Subrutas anidadas */}
          <Route index element={<Navigate to="mis-datos" replace />} />
          <Route path="mis-datos" element={<MisDatos />} />
          <Route path="pedidos" element={<Pedidos />} />
          <Route path="lista-deseos" element={<ListaDeseos />} />

          {/* Si quieres mostrar algo por defecto al entrar en /usuario */}

        </Route>
      </Routes>
    </div>
  );
}

export default App;
