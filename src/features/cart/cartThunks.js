import { createAsyncThunk } from '@reduxjs/toolkit';
import api from '../../api/api';

// Add an item to the cart
export const addItemToCart = createAsyncThunk('cart/addItem', async (productData, { rejectWithValue }) => {
  try {
    const response = await api.put('/api/cart/add', productData, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
    return response.data;
  } catch (error) {
    return rejectWithValue(error.response ? error.response.data.message : 'Failed to add item to cart');
  }
});

// Update an item in the cart
export const updateCartItem = createAsyncThunk('cart/updateItem', async ({ cartItemId, cartItemData }, { rejectWithValue }) => {
  try {
    const response = await api.put(`/api/cart_items/${cartItemId}`, cartItemData, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
    return response.data;
  } catch (error) {
    return rejectWithValue(error.response ? error.response.data.message : 'Failed to update cart item');
  }
});

// Remove an item from the cart
export const removeCartItem = createAsyncThunk('cart/removeItem', async (cartItemId, { rejectWithValue }) => {
  try {
    await api.delete(`/api/cart_items/${cartItemId}`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
    return cartItemId;
  } catch (error) {
    return rejectWithValue(error.response ? error.response.data.message : 'Failed to remove item from cart');
  }
});
