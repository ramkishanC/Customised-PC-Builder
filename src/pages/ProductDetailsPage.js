// src/pages/ProductDetailsPage.js
import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProductById } from '../features/product/productSlice';
import { useParams } from 'react-router-dom';
import { Card, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductDetailsPage = () => {
  const { productId } = useParams();
  const dispatch = useDispatch();
  const product = useSelector((state) => state.products.items.find((p) => p.id === Number(productId)));

  useEffect(() => {
    if (!product) {
      dispatch(fetchProductById(productId));
    }
  }, [dispatch, product, productId]);

  if (!product) {
    return <p>Loading...</p>;
  }

  return (
    <div className="container mt-4">
      <Card className="shadow-sm">
        <Card.Body>
          <Card.Title>{product.name}</Card.Title>
          <Card.Subtitle className="mb-2 text-muted">{`Price: $${product.price}`}</Card.Subtitle>
          <Card.Text>{`Quantity: ${product.quantity}`}</Card.Text>
          <Card.Text>{`Description: ${product.description}`}</Card.Text>
          <Button variant="primary" onClick={() => window.history.back()}>
            Back to Dashboard
          </Button>
        </Card.Body>
      </Card>
    </div>
  );
};

export default ProductDetailsPage;
