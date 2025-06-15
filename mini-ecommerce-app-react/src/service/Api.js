import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    withCredentials: true // Esto permite enviar cookies HttpOnly automáticamente
});

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('accessToken');
        if (token) config.headers.Authorization = `Bearer ${token}`;
        return config;
    }
);

api.interceptors.response.use(
    (response) => response,
    async (error) => {
        const originalRequest = error.config;
        
        if (error.response && error.response.status === 401 && !originalRequest._retry) {
            console.log('Interceptado 401, intentando refrescar token...');

            originalRequest._retry = true;

            try {
                const res = await axios.post('http://localhost:8080/api/auth/refresh', {}, { withCredentials: true });
                localStorage.setItem('accessToken', res.data.accessToken);
                console.log('Nuevo accessToken:', res.data.accessToken);

                return api(originalRequest); // Retry original request
            } catch (refreshError) {
                console.error('Error al renovar token:', refreshError);
                // Opcional: Logout del usuario
            }
        }
        return Promise.reject(error);
    }
);

export default api;
