-- MySQL schema for Part B - Employees
CREATE DATABASE IF NOT EXISTS companydb;
USE companydb;

CREATE TABLE IF NOT EXISTS employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  department VARCHAR(100) NOT NULL,
  salary DECIMAL(10,2) NOT NULL
);

INSERT INTO employees (name, department, salary) VALUES
('Alice Johnson', 'Engineering', 90000.00),
('Bob Smith', 'Sales', 75000.00),
('Carol Davis', 'HR', 68000.00)
ON DUPLICATE KEY UPDATE name=VALUES(name);


