CREATE TABLE employees_sb_jdbc (
    id NUMBER PRIMARY KEY GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    designation VARCHAR(50),
    age INT,
    address TEXT,
    dob DATE,
    salary DECIMAL(10, 2)
);
