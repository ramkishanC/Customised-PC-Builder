import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addProduct } from '../features/product/productSlice';
import { useNavigate } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';

const AddProductPage = () => {
  const [productData, setProductData] = useState({
    name: '',
    description: '',
    price: '',
    quantity: '',
    brand: '',
    category: ''
  });
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProductData({
      ...productData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const resultAction = await dispatch(addProduct(productData));
      
      if (addProduct.fulfilled.match(resultAction)) {
        console.log('Product added successfully:', resultAction.payload);
        navigate('/admin-dashboard');
      } else {
        console.log('Failed to add product:', resultAction.payload);
      }
    } catch (error) {
      console.error('Error adding product:', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Add New Product</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formProductName">
          <Form.Label>Product Name</Form.Label>
          <Form.Control
            type="text"
            name="name"
            value={productData.name}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formProductDescription">
          <Form.Label>Description</Form.Label>
          <Form.Control
            type="text"
            name="description"
            value={productData.description}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formProductPrice">
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            name="price"
            value={productData.price}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formProductQuantity">
          <Form.Label>Quantity</Form.Label>
          <Form.Control
            type="number"
            name="quantity"
            value={productData.quantity}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formProductBrand">
          <Form.Label>Brand</Form.Label>
          <Form.Control
            type="text"
            name="brand"
            value={productData.brand}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formProductCategory">
          <Form.Label>Category</Form.Label>
          <Form.Control
            type="text"
            name="category"
            value={productData.category}
            onChange={handleInputChange}
            required
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Add Product
        </Button>
      </Form>
    </div>
  );
};

export default AddProductPage;
