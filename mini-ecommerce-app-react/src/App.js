import { Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Productos from './components/Productos';
import Login from './components/Login';
import Usuario from './components/Usuario';
import ProtectedRoute from './components/ProtectedRoute';
import Cesta from './components/Cesta';

function App() {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/productos" element={<Productos />} />
        <Route path="/login" element={<Login />} />
        <Route path='/cesta' element={<Cesta /> } />
        <Route
          path="/usuario"
          element={
            <ProtectedRoute>
              <Usuario />
            </ProtectedRoute>
          }
        />
      </Routes>
    </div>
  );
}

export default App;
