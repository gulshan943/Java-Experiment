-- MySQL schema for Part C - Attendance
CREATE DATABASE IF NOT EXISTS schooldb;
USE schooldb;

CREATE TABLE IF NOT EXISTS attendance (
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id VARCHAR(50) NOT NULL,
  class_date DATE NOT NULL,
  status ENUM('Present','Absent','Late') NOT NULL,
  UNIQUE KEY unique_attendance (student_id, class_date)
);


