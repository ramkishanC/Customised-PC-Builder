import { createSlice } from '@reduxjs/toolkit';
import api from '../../api/api';

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    user: null,
    token: null,
    role: null,
  },
  reducers: {
    setUser: (state, action) => {
      state.user = action.payload.user;
      state.token = action.payload.token;
      state.role = action.payload.role;
    },
    logoutUser: (state) => {
      state.user = null;
      state.token = null;
      state.role = null;
      localStorage.removeItem('token'); // Clear token from localStorage
    },
  },
});

export const { setUser, logoutUser } = authSlice.actions;

export const loginUser = (credentials, isAdmin = false) => async (dispatch) => {
  try {
    const { data } = await api.post('/auth/signin', credentials);
    if (data.status) {
      const role = isAdmin ? 'ADMIN' : 'CUSTOMER';
      dispatch(setUser({ user: data.user, token: data.jwt, role }));
      localStorage.setItem('token', data.jwt); // Store token in localStorage
      return { success: true, role }; // Return success and role
    } else {
      return { success: false, message: data.message }; // Return failure and message
    }
  } catch (error) {
    console.error('Failed to log in:', error);
    return { success: false, message: 'Login failed. Please try again.' }; // Return failure and message
  }
};

export const signupUser = (userData) => async (dispatch) => {
  try {
    const { data } = await api.post('/auth/signup', userData);
    dispatch(setUser({ user: data.user, token: data.token }));
    localStorage.setItem('token', data.token); // Store token in localStorage
  } catch (error) {
    console.error('Failed to sign up:', error);
    // Optionally, handle error (e.g., show a message to the user)
  }
};

export default authSlice.reducer;
