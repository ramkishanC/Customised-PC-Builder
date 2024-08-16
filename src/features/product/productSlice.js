import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import api from '../../api/api';

// Fetch all products
export const fetchProducts = createAsyncThunk('products/fetchAll', async () => {
  try {
    const response = await api.get('/api/products/all');
    return response.data;
  } catch (error) {
    console.error('Failed to fetch products:', error);
    return Promise.reject(error.response ? error.response.data.message : 'Failed to fetch products');
  }
});

// Fetch a product by ID
export const fetchProductById = createAsyncThunk('products/fetchById', async (productId) => {
  try {
    const response = await api.get(`/api/products/${productId}`);
    return response.data;
  } catch (error) {
    console.error('Failed to fetch product by ID:', error);
    return Promise.reject(error.response ? error.response.data.message : 'Failed to fetch product');
  }
});

// Add a new product
export const addProduct = createAsyncThunk('products/add', async (productData) => {
  try {
    const response = await api.post('/api/admin/products/', productData);
    return response.data;
  } catch (error) {
    console.error('Failed to add product:', error);
    return Promise.reject(error.response ? error.response.data.message : 'Failed to add product');
  }
});

// Update an existing product
export const updateProduct = createAsyncThunk('products/update', async ({ productId, productData }) => {
  try {
    const response = await api.put(`/api/admin/products/${productId}/update`, productData);
    return response.data;
  } catch (error) {
    console.error('Failed to update product:', error);
    return Promise.reject(error.response ? error.response.data.message : 'Failed to update product');
  }
});

// Delete a product
export const deleteProduct = createAsyncThunk('products/delete', async (productId) => {
  try {
    await api.delete(`/api/admin/products/${productId}/delete`);
    return productId;
  } catch (error) {
    console.error('Failed to delete product:', error);
    return Promise.reject(error.response ? error.response.data.message : 'Failed to delete product');
  }
});

const productSlice = createSlice({
  name: 'products',
  initialState: {
    items: [],
    status: 'idle',
    error: null
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchProducts.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchProducts.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.items = action.payload;
      })
      .addCase(fetchProducts.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(fetchProductById.fulfilled, (state, action) => {
        // Optional: handle specific product fetched by ID
      })
      .addCase(addProduct.fulfilled, (state, action) => {
        state.items.push(action.payload);
      })
      .addCase(updateProduct.fulfilled, (state, action) => {
        const index = state.items.findIndex(product => product.id === action.payload.id);
        if (index !== -1) {
          state.items[index] = action.payload;
        }
      })
      .addCase(deleteProduct.fulfilled, (state, action) => {
        state.items = state.items.filter(product => product.id !== action.payload);
      });
  }
});

export default productSlice.reducer;
