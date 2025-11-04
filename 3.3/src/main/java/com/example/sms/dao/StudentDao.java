package com.example.sms.dao;

import java.util.List;

import com.example.sms.entity.Student;

public interface StudentDao {
    Long save(Student student);
    void update(Student student);
    void deleteById(Long id);
    Student findById(Long id);
    List<Student> findAll();
}


