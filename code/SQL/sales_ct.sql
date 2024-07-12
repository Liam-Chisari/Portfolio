CREATE TABLE sales_data (
     sale_id INT AUTO_INCREMENT PRIMARY KEY,
     client_id INT NOT NULL,
     client_name VARCHAR(50) NOT NULL,
     product_id INT NOT NULL,
     product_price DECIMAL(10, 2) NOT NULL,
     quantity INT NOT NULL,
     sale_amount DECIMAL(10, 2) NOT NULL,
     sale_date DATE NOT NULL,
     sale_time TIME NOT NULL,
     payment_method VARCHAR(20) NOT NULL,
     salesperson_id INT NOT NULL,
     salesperson_name VARCHAR(50) NOT NULL,
     commission_rate DECIMAL(4, 2) NOT NULL,
     notes TEXT NULL
     -- FOREIGN KEY (client_id) REFERENCES clients(client_id),
     -- FOREIGN KEY (product_id) REFERENCES products(product_id),
     -- FOREIGN KEY (salesperson_id) REFERENCES salespersons(salesperson_id)
);
