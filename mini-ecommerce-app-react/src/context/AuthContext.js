import { createContext, useContext, useEffect, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const navigate = useNavigate();
    const [user, setUser] = useState(() => {
        const saved = localStorage.getItem('user');
        return saved ? JSON.parse(saved) : null;
    });

    const token = localStorage.getItem('token');

    const logout = useCallback(() => {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        setUser(null);
        navigate('/login');
    }, [navigate]);

    const loadUser = useCallback(async () => {
        if (!token) return;
        try {
            const response = await axios.get('http://localhost:8080/api/usuarios/me', {
                headers: { Authorization: `Bearer ${token}` },
            });
            setUser(response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
        } catch (err) {
            console.error("Error al cargar el usuario:", err);
            logout();
        }
    }, [token, logout]); // <<--- ahora logout está definido antes, así no hay warning

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
