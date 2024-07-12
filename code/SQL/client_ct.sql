CREATE TABLE clients (
    client_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique client identifier
    first_name VARCHAR(50) NOT NULL,         -- Client's first name
    last_name VARCHAR(50) NOT NULL,          -- Client's last name
    email VARCHAR(100) UNIQUE,                -- Client's email (unique)
    phone VARCHAR(20),                        -- Client's phone number
    company VARCHAR(100),                     -- Client's company (if applicable)
    address VARCHAR(255),                      -- Client's address
    state VARCHAR(50),                        -- Client's state/province
    postal_code VARCHAR(20),                   -- Client's postal/zip code
    total_orders INT DEFAULT 0,              -- Total number of orders
    monthly_orders INT DEFAULT 0,             -- Number of orders this month
    most_ordered_category VARCHAR(50)         -- Category of most ordered item
);
