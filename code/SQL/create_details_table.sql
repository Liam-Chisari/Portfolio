CREATE TABLE product_details (
    stock_id INT PRIMARY KEY, -- Shared with 'stock' table
    description TEXT,
    ingredients TEXT,
    origin VARCHAR(50),
    is_organic TINYINT(1),  -- Use 1 for 'Yes', 0 for 'No'
    FOREIGN KEY (stock_id) REFERENCES stock(stock_id) 
); 
