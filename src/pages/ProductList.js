import React from 'react';
import ProductCard from './ProductCard';

const ProductList = ({ products }) => {
  return (
    <div className="row">
      {products.map((product) => (
        <div key={product.id} className="col-md-4 mb-4">
          <ProductCard product={product} />
        </div>
      ))}
    </div>
  );
};

export default ProductList;
