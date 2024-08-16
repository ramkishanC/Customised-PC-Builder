// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './store/store';
import Navbar from './components/Navbar';
import UserLoginPage from './pages/UserLoginPage';
import AdminLoginPage from './pages/AdminLoginPage';
import SignupPage from './pages/SignupPage';
import ProductPage from './pages/ProductPage';
import ProductDetailsPage from './pages/ProductDetailsPage';
import CartPage from './pages/CartPage';
import UserDashboard from './pages/UserDashboard';
import AdminDashboard from './pages/AdminDashboard';
import AddProductPage from './pages/AddProductPage';
import EditProductPage from './pages/EditProductPage';

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <Navbar />
        <div className="container mt-4">
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<ProductPage />} />
            <Route path="/login" element={<UserLoginPage />} />
            <Route path="/admin-login" element={<AdminLoginPage />} />
            <Route path="/signup" element={<SignupPage />} />
            <Route path="/products/:productId" element={<ProductDetailsPage />} />
            <Route path="/cart" element={<CartPage />} />

            {/* Protected Routes */}
            <Route path="/user-dashboard" element={<UserDashboard />} />
            <Route path="/admin-dashboard" element={<AdminDashboard />} />

            {/* Admin Routes */}
            <Route path="/add-product" element={<AddProductPage />} />
            <Route path="/edit-product/:productId" element={<EditProductPage />} />

            <Route path="/login" element={<UserLoginPage />} />
            <Route path="/user-dashboard" element={<UserDashboard />} />

          </Routes>
        </div>
      </Router>
    </Provider>
  );
};

export default App;