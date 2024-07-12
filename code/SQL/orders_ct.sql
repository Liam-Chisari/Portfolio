CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,    -- Unique order identifier
    client_id INT NOT NULL,                    -- Foreign key to the clients table
    order_date DATE,                            -- Date the order was placed
    total_amount DECIMAL(10, 2),                 -- Total order amount
    status VARCHAR(20) DEFAULT 'Pending',       -- Order status (Pending, Processing, Shipped, etc.)
    payment_method VARCHAR(50),                  -- Payment method (Credit Card, PayPal, etc.)
    shipping_address VARCHAR(255),               -- Shipping address
    shipping_method VARCHAR(50),                 -- Shipping method (Standard, Express, etc.)
    estimated_delivery DATE,                     -- Estimated delivery date
    items JSON,                                 -- JSON array to store order items and quantities

    FOREIGN KEY (client_id) REFERENCES clients(client_id)
);
