package com.example.hibernate;

import com.example.hibernate.dao.StudentDao;
import com.example.hibernate.entity.Student;
import com.example.hibernate.util.HibernateUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();

        // Create
        Long id = dao.save(new Student("Alice", "alice@example.com"));
        System.out.println("Saved student with id: " + id);

        // Read
        Student s = dao.findById(id);
        System.out.println("Found: " + s);

        // Update
        s.setName("Alice Updated");
        dao.update(s);
        System.out.println("Updated: " + dao.findById(id));

        // List all
        List<Student> all = dao.findAll();
        System.out.println("All students: " + all);

        // Delete
        dao.delete(id);
        System.out.println("Deleted id: " + id);

        HibernateUtil.shutdown();
    }
}


