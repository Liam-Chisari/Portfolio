CREATE TABLE stock (
    stock_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    cost_price DECIMAL(10,2) NOT NULL,
    sale_price DECIMAL(10,2) NOT NULL,
    number_in_stock INT NOT NULL,
    date_ordered DATE,
    units_sold_year INT,
    units_sold_month INT,
    avg_units_per_customer DECIMAL(5,2),  
    category VARCHAR(50),  -- E.g., 'Cheese', 'Pasta', 'Cured Meats'
    expiry_date DATE   -- Track product expiration  
);
