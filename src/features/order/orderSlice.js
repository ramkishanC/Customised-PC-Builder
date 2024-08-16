import { createSlice } from '@reduxjs/toolkit';
import api from '../../api/api';

const orderSlice = createSlice({
  name: 'order',
  initialState: {
    orders: [],
  },
  reducers: {
    addOrder: (state, action) => {
      state.orders.push(action.payload);
    },
  },
});

export const { addOrder } = orderSlice.actions;

export const placeOrder = (orderData) => async (dispatch) => {
  try {
    const response = await api.post('/orders', orderData);
    dispatch(addOrder(response.data));
  } catch (error) {
    console.error('Failed to place order:', error);
  }
};

export default orderSlice.reducer;
