import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserDashboard = () => {
  const [userProfile, setUserProfile] = useState(null);
  const [products, setProducts] = useState([]);
  const [loadingProfile, setLoadingProfile] = useState(true);
  const [loadingProducts, setLoadingProducts] = useState(true);
  const [errorProfile, setErrorProfile] = useState(null);
  const [errorProducts, setErrorProducts] = useState(null);

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          throw new Error('No token found');
        }
        
        const response = await axios.get('/api/users/profile', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        setUserProfile(response.data);
        setLoadingProfile(false);
      } catch (err) {
        setErrorProfile(err.response ? err.response.data.message : 'Failed to fetch user profile');
        setLoadingProfile(false);
      }
    };

    const fetchProducts = async () => {
      try {
        const response = await axios.get('/api/products/all');
        setProducts(response.data);
        setLoadingProducts(false);
      } catch (err) {
        setErrorProducts(err.response ? err.response.data.message : 'Failed to fetch products');
        setLoadingProducts(false);
      }
    };

    fetchUserProfile();
    fetchProducts();
  }, []);

  if (loadingProfile || loadingProducts) return <p>Loading...</p>;
  if (errorProfile) return <p>{errorProfile}</p>;
  if (errorProducts) return <p>{errorProducts}</p>;

  return (
    <div className="container">
      <h2>User Dashboard</h2>
      <div className="profile-section">
        <h3>Profile</h3>
        {userProfile ? (
          <>
            <p><strong>Name:</strong> {userProfile.firstName} {userProfile.lastName}</p>
            <p><strong>Email:</strong> {userProfile.email}</p>
            <p><strong>Mobile:</strong> {userProfile.mobile}</p>
            <p><strong>Role:</strong> {userProfile.role}</p>
          </>
        ) : (
          <p>No profile information available.</p>
        )}
      </div>

      <div className="products-section">
        <h3>Products</h3>
        {products.length > 0 ? (
          <ul>
            {products.map((product) => (
              <li key={product.id}>
                {product.name} - ${product.price}
              </li>
            ))}
          </ul>
        ) : (
          <p>No products available.</p>
        )}
      </div>
    </div>
  );
};

export default UserDashboard;
