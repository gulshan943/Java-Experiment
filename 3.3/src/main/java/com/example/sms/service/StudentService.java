package com.example.sms.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.sms.entity.Student;

public interface StudentService {
    Long createStudent(String name, String email);
    void updateStudent(Student student);
    void deleteStudent(Long id);
    Student getStudent(Long id);
    List<Student> listStudents();

    void payFees(Long studentId, BigDecimal amount);
    void refundFees(Long studentId, BigDecimal amount);
}


