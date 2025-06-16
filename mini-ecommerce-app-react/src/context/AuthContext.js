import { createContext, useContext, useEffect, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { obtenerDatos } from '../service/AuthService';

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const navigate = useNavigate();
    const [token, setToken] = useState(() => localStorage.getItem('accessToken'));
    const [user, setUser] = useState(null);

    const logout = useCallback(() => {
        localStorage.removeItem('accessToken');
        setToken(null);
        setUser(null);
        navigate('/login');
    }, [navigate]);

    const updateToken = (newToken) => {
        localStorage.setItem('accessToken', newToken);
        setToken(newToken);
    };

    const loadUser = useCallback(async () => {
        if (!token) return;
        try {
            const response = await obtenerDatos();
            setUser(response.data);
        } catch (err) {
            console.error("Error al cargar el usuario:", err);
            // logout();
        }
    }, [token, logout]);

    useEffect(() => {
        if (token && !user) {
            loadUser();
        }
    }, [token, user, loadUser]);

    return (
        <AuthContext.Provider value={{ user, setUser, token, setToken: updateToken, logout, loadUser }}>
            {children}
        </AuthContext.Provider>
    );
}


export function useAuth() {
    return useContext(AuthContext);
}
