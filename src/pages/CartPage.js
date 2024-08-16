import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import { placeOrder } from '../features/order/orderSlice';
import { removeFromCart, updateCartItem } from '../features/cart/cartSlice';

const CartPage = () => {
  const dispatch = useDispatch();
  const cartItems = useSelector((state) => state.cart.items);
  const totalPrice = useSelector((state) => state.cart.totalPrice);
  const totalItems = useSelector((state) => state.cart.totalItems);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await axios.get('/api/cart/', {
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          }
        });

        // Update the Redux store with the fetched cart items
        const { items, totalPrice, totalItems } = response.data;
        dispatch({ type: 'cart/setCartItems', payload: { items, totalPrice, totalItems } });
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch cart items');
        setLoading(false);
      }
    };

    fetchCartItems();
  }, [dispatch]);

  const handlePlaceOrder = () => {
    dispatch(placeOrder({ items: cartItems, totalPrice }));
  };

  const handleRemoveItem = async (itemId) => {
    try {
      await axios.delete(`/api/cart_items/${itemId}`, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });
      dispatch(removeFromCart(itemId));
    } catch (err) {
      setError('Failed to remove item from cart');
    }
  };

  const handleUpdateItem = async (itemId, updatedQuantity) => {
    if (updatedQuantity < 1) return; // Prevent setting quantity to less than 1

    try {
      const response = await axios.put(`/api/cart_items/${itemId}`, { quantity: updatedQuantity }, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });

      dispatch(updateCartItem(response.data));
    } catch (err) {
      setError('Failed to update cart item');
    }
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="container">
      <h2>Cart</h2>
      {cartItems.length > 0 ? (
        <>
          {cartItems.map((item) => (
            <div key={item.id} className="mb-3">
              <h5>{item.name}</h5>
              <p>
                Quantity: 
                <input
                  type="number"
                  value={item.quantity}
                  min="1"
                  onChange={(e) => handleUpdateItem(item.id, parseInt(e.target.value))}
                />
              </p>
              <p>Price: ${item.price * item.quantity}</p>
              <button className="btn btn-danger" onClick={() => handleRemoveItem(item.id)}>
                Remove
              </button>
            </div>
          ))}
          <h3>Total Items: {totalItems}</h3>
          <h3>Total Price: ${totalPrice}</h3>
          <button className="btn btn-primary" onClick={handlePlaceOrder}>
            Place Order
          </button>
        </>
      ) : (
        <p>Your cart is empty.</p>
      )}
    </div>
  );
};

export default CartPage;
