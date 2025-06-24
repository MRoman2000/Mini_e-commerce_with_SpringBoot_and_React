import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    withCredentials: true,
});

let isRefreshing = false;
let refreshSubscribers = [];

function onRefreshed(token) {
    refreshSubscribers.forEach(callback => callback(token));
    refreshSubscribers = [];
}

function addRefreshSubscriber(callback) {
    refreshSubscribers.push(callback);
}

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('accessToken');
    if (token) config.headers.Authorization = `Bearer ${token}`;
    return config;
});

api.interceptors.response.use(
    (response) => response,
    (error) => {
        const { config, response } = error;
        const originalRequest = config;

        if (response && response.status === 401 && !originalRequest._retry) {
            if (isRefreshing) {
                // Ya hay un refresh en curso, devolvemos una promesa que espera a que se actualice el token
                return new Promise((resolve) => {
                    addRefreshSubscriber((token) => {
                        originalRequest.headers.Authorization = 'Bearer ' + token;
                        resolve(api(originalRequest));
                    });
                });
            }

            originalRequest._retry = true;
            isRefreshing = true;

            return new Promise(async (resolve, reject) => {
                try {
                    const res = await axios.post('http://localhost:8080/api/auth/refresh', {}, { withCredentials: true });
                    const newToken = res.data.accessToken;
                    localStorage.setItem('accessToken', newToken);
                    api.defaults.headers.common['Authorization'] = 'Bearer ' + newToken;
                    onRefreshed(newToken);
                    resolve(api(originalRequest));
                } catch (err) {
                    reject(err);
                  
                } finally {
                    isRefreshing = false;
                }
            });
        }

        return Promise.reject(error);
    }
);

export default api;
