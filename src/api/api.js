// src/api/api.js
import axios from 'axios';
import { store } from '../store/store';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Your backend URL
});

// Request interceptor to add token to headers
api.interceptors.request.use(
  (config) => {
    const state = store.getState();
    const token = state.auth.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
