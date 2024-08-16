import React from 'react';
import { Link } from 'react-router-dom';
import { Button, Container, Row, Col, Card } from 'react-bootstrap';
import './HomePage.css'; // Make sure to create and add styles here

const HomePage = () => {
  // Dummy data for featured products
  const featuredProducts = [
    { id: 1, name: 'Gaming PC', price: '$999' },
    { id: 2, name: 'Office Laptop', price: '$599' },
    { id: 3, name: 'Ultra HD Monitor', price: '$299' },
  ];

  return (
    <Container className="mt-4">
      <section className="hero-section mb-4 text-center">
        <h1>Welcome to Our PC Store!</h1>
        <p>Your one-stop shop for all things PC. Browse our selection of high-quality components and pre-built systems.</p>
        <Button variant="primary" as={Link} to="/products">
          Shop Now
        </Button>
      </section>

      <section className="featured-products mb-4">
        <h2>Featured Products</h2>
        <Row>
          {featuredProducts.map((product) => (
            <Col md={4} key={product.id} className="mb-4">
              <Card>
                <Card.Body>
                  <Card.Title>{product.name}</Card.Title>
                  <Card.Text>
                    {product.price}
                  </Card.Text>
                  <Button variant="primary" as={Link} to={`/products/${product.id}`}>
                    View Details
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </section>

      <section className="about-us text-center mt-4">
        <h2>About Us</h2>
        <p>We are dedicated to providing top-notch PC components and systems. Our mission is to offer the best quality products at competitive prices.</p>
        <Button variant="secondary" as={Link} to="/about">
          Learn More
        </Button>
      </section>
    </Container>
  );
};

export default HomePage;
