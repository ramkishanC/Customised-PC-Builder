import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts } from '../features/product/productSlice';
import { getUserProfile } from '../features/auth/authSlice';
import { useNavigate } from 'react-router-dom';
import { Button, Card, Col, Row } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const UserDashboard = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [userProfile, setUserProfile] = useState(null);
  const { items: products, status } = useSelector((state) => state.products);
  const token = useSelector((state) => state.auth.token);

  useEffect(() => {
    if (!token) {
      navigate('/login');
    } else {
      // Fetch products
      dispatch(fetchProducts());

      // Fetch user profile
      dispatch(getUserProfile(token)).then((response) => {
        setUserProfile(response.payload);
      });
    }
  }, [dispatch, token, navigate]);

  if (!token) {
    return <p>Please log in to view this page.</p>;
  }

  if (status === 'loading') {
    return <p>Loading...</p>;
  }

  if (status === 'failed') {
    return <p>Error loading products</p>;
  }

  return (
    <div className="container mt-4">
      <h2>User Dashboard</h2>
      {userProfile && (
        <div className="mb-4">
          <h3>Welcome, {userProfile.firstname}!</h3>
          <p>Email: {userProfile.email}</p>
          <p>Mobile: {userProfile.mobile}</p>
        </div>
      )}
      <h3 className="mb-3">Products</h3>
      {Array.isArray(products) && products.length > 0 ? (
        <Row xs={1} md={2} lg={3} className="g-4">
          {products.map((product) => (
            <Col key={product.id}>
              <Card className="shadow-sm">
                <Card.Body>
                  <Card.Title>{product.name}</Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">{`Price: $${product.price}`}</Card.Subtitle>
                  <Card.Text>{`Quantity: ${product.quantity}`}</Card.Text>
                  <Card.Text>{`ID: ${product.id}`}</Card.Text>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      ) : (
        <p>No products available</p>
      )}
    </div>
  );
};

export default UserDashboard;
