import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts } from '../features/product/productSlice';

const ProductPage = () => {
  const dispatch = useDispatch();
  const products = useSelector((state) => state.products.items);
  const productStatus = useSelector((state) => state.products.status);
  const error = useSelector((state) => state.products.error);

  useEffect(() => {
    if (productStatus === 'idle') {
      dispatch(fetchProducts());
    }
  }, [productStatus, dispatch]);

  return (
    <div>
      {productStatus === 'loading' && <p>Loading...</p>}
      {productStatus === 'failed' && <p>{error}</p>}
      {productStatus === 'succeeded' && (
        <ul>
          {products.map((product) => (
            <li key={product.id}>
              <h2>{product.name}</h2>
              <p>Description: {product.description}</p>
              <p>Price: ${product.price}</p>
              <p>Quantity: {product.quantity}</p>
              <p>Brand: {product.brand}</p>
              <p>Category: {product.category}</p>
              <p>Number of Ratings: {product.numRating}</p>
              {/* Optionally include rating and review details if needed */}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ProductPage;
