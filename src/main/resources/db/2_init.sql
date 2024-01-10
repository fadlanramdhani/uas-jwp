CREATE TABLE passenger (
    id INT AUTO_INCREMENT PRIMARY KEY,
    passid VARCHAR(10) UNIQUE,
    passenger_name VARCHAR(50),
    address TEXT(30),
    phone_number VARCHAR(15) 
    );