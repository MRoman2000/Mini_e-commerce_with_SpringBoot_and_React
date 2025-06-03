import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Productos from './components/Productos';
import Login from './components/Login';
import Usuario from './components/Usuario';
import ProtectedRoute from './components/ProtectedRoute';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/productos" element={<Productos />} />
          <Route path="/login" element={<Login />} />
          <Route
            path="/usuario"
            element={
              <ProtectedRoute>
                <Usuario />
              </ProtectedRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
