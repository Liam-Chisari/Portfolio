CREATE TABLE Books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author_first_name VARCHAR(100) NOT NULL,
    author_last_name VARCHAR(100) NOT NULL,
    isbn_13 VARCHAR(13) UNIQUE,
    isbn_10 VARCHAR(10), 
    publication_year SMALLINT,
    edition VARCHAR(50),
    language VARCHAR(50),
    format VARCHAR(50), 
    pages INT,
    category VARCHAR(100),
    description TEXT,
    average_rating DECIMAL(2, 1) 
); 
