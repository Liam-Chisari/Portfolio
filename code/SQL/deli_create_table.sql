CREATE TABLE stock (
    stock_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    cost_price DECIMAL(10,2) NOT NULL,
    sale_price DECIMAL(10,2) NOT NULL,
    number_in_stock INT NOT NULL,
    date_ordered DATE NOT NULL,
    units_sold_year INT NOT NULL,
    units_sold_month INT NOT NULL,
    avg_units_per_customer DECIMAL(5,2) NOT NULL,
    unique_field_1 TEXT, -- Example 1: Category (Pantry, Charcuterie, Cheese, etc.)
    unique_field_2 BOOLEAN NOT NULL DEFAULT 0  -- Example 2: Perishable flag 
); 
