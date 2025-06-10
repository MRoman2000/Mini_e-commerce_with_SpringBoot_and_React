import { createContext, useContext, useEffect, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { obtenerDatos } from '../service/AuthService';

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const navigate = useNavigate();
    const [user, setUser] = useState(() => {
      //  const saved = localStorage.getItem('user');
     //   return saved ? JSON.parse(saved) : null;
    });

    const token = localStorage.getItem('token');

    const logout = useCallback(() => {
        localStorage.removeItem('token');
      //  localStorage.removeItem('user');
        setUser(null);
        navigate('/login');
    }, [navigate]);

    const loadUser = useCallback(async () => {
        if (!token) return;
        try {
            const response = await obtenerDatos(token);
            setUser(response.data);
            console.log(response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
        } catch (err) {
            console.error("Error al cargar el usuario:", err);
            logout();
        }
    }, [token, logout]);

    useEffect(() => {
        if (token && !user) {
            loadUser();
        }
    }, [token, user, loadUser]);

    return (
        <AuthContext.Provider value={{ user, setUser, token, logout, loadUser }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
