import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts, deleteProduct } from '../features/product/productSlice';
import { logoutUser } from '../features/auth/authSlice';
import { useNavigate } from 'react-router-dom';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import { Button, Card, Col, Row, Modal } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminDashboard = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { items: products, status } = useSelector((state) => state.products);
  const token = useSelector((state) => state.auth.token);
  const [showModal, setShowModal] = useState(false);
  const [selectedProductId, setSelectedProductId] = useState(null);

  useEffect(() => {
    if (!token) {
      navigate('/admin-login');
    } else {
      dispatch(fetchProducts());
    }
  }, [dispatch, token, navigate]);

  const handleDeleteProduct = (productId) => {
    setSelectedProductId(productId);
    setShowModal(true);
  };

  const confirmDelete = () => {
    dispatch(deleteProduct(selectedProductId)).then(() => {
      dispatch(fetchProducts());
      setShowModal(false);
    }).catch((error) => {
      console.error('Failed to delete product:', error);
    });
  };

  const handleLogout = () => {
    dispatch(logoutUser());
    navigate('/admin-login');
  };

  if (status === 'loading') {
    return <p>Loading...</p>;
  }

  if (status === 'failed') {
    return <p>Error loading products</p>;
  }

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Admin Dashboard</h2>
      <div className="d-flex justify-content-between mb-3">
        <Button variant="primary" onClick={() => navigate('/add-product')}>
          Add New Product
        </Button>
        <Button variant="secondary" onClick={handleLogout}>
          Logout
        </Button>
      </div>
      <h3 className="mb-3">Product List</h3>
      {Array.isArray(products) && products.length > 0 ? (
        <Row xs={1} md={2} lg={3} className="g-4">
          {products.map((product) => (
            <Col key={product.id}>
              <Card className="shadow-sm" style={{ cursor: 'pointer' }}>
                <Card.Body>
                  <Card.Title>{product.name}</Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">Price: ${product.price}</Card.Subtitle>
                  <Card.Text>Quantity: {product.quantity}</Card.Text>
                  <Card.Text>ID: {product.id}</Card.Text>
                  <div className="d-flex justify-content-end">
                    <Button
                      variant="warning"
                      className="me-2"
                      onClick={(e) => {
                        e.stopPropagation();
                        navigate(`/edit-product/${product.id}`);
                      }}
                    >
                      <FaEdit /> Edit
                    </Button>
                    <Button
                      variant="danger"
                      onClick={(e) => {
                        e.stopPropagation();
                        handleDeleteProduct(product.id);
                      }}
                    >
                      <FaTrashAlt /> Delete
                    </Button>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      ) : (
        <p>No products found</p>
      )}
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Confirm Deletion</Modal.Title>
        </Modal.Header>
        <Modal.Body>Are you sure you want to delete this product?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Cancel
          </Button>
          <Button variant="danger" onClick={confirmDelete}>
            Confirm
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default AdminDashboard;
