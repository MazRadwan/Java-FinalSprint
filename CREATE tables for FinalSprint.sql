BEGIN;

-- Create ENUM type for roles
CREATE TYPE user_role AS ENUM ('admin', 'seller', 'buyer');

-- Create Users Table
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role user_role NOT NULL
);

-- Create Products Table
CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    quantity INT NOT NULL,
    seller_id INT,
    description TEXT,
    FOREIGN KEY (seller_id) REFERENCES Users(user_id)
);

-- Create Function to Check User Role
CREATE OR REPLACE FUNCTION check_seller_role() RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT role FROM Users WHERE user_id = NEW.seller_id) <> 'seller' THEN
        RAISE EXCEPTION 'seller_id must reference a user with role ''seller''';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create Trigger
CREATE TRIGGER validate_seller_role 
BEFORE INSERT OR UPDATE ON Products
FOR EACH ROW
EXECUTE FUNCTION check_seller_role();

COMMIT;
