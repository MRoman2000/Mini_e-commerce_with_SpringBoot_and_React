import { Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Home from './components/navbarPages/Home';
import Productos from './components/navbarPages/Productos';
import Login from './components/Login';
import Usuario from './components/Usuario';
import ProtectedRoute from './components/ProtectedRoute';
import Cesta from './components/Cesta';
import Pedidos from './components/pages/Pedidos';
import ListaDeseos from './components/pages/ListaDeseos';
import MisDatos from './components/pages/MisDatos';
import { Navigate } from "react-router-dom";
import Register from './components/pages/Register';
import Admin from './components/navbarPages/Admin';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const queryClient = new QueryClient();

function App() {
  return (
    <div className="App">
      <QueryClientProvider client={queryClient}>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/productos" element={<Productos />} />
          <Route path="/login" element={<Login />} />
          <Route path="/cesta" element={<Cesta />} />
          <Route path="/register" element={<Register />} />

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
            <Route
              path="/usuario/admin"
              element={
                <ProtectedRoute requiredRole="ROLE_ADMIN">
                  <Admin />
                </ProtectedRoute>
              }
            />

          </Route>
        </Routes>
      </QueryClientProvider>
    </div>
  );
}

export default App;