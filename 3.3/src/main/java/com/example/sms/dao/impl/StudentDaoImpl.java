package com.example.sms.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.sms.dao.StudentDao;
import com.example.sms.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Long save(Student student) {
        return (Long) currentSession().save(student);
    }

    @Override
    public void update(Student student) {
        currentSession().update(student);
    }

    @Override
    public void deleteById(Long id) {
        Student s = findById(id);
        if (s != null) {
            currentSession().delete(s);
        }
    }

    @Override
    public Student findById(Long id) {
        return currentSession().get(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        Query<Student> q = currentSession().createQuery("from Student order by id", Student.class);
        return q.getResultList();
    }
}


