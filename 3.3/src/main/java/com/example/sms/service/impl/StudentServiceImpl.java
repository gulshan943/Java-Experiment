package com.example.sms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sms.dao.StudentDao;
import com.example.sms.entity.FeeTransaction;
import com.example.sms.entity.Student;

@Service
public class StudentServiceImpl implements com.example.sms.service.StudentService {

    private final StudentDao studentDao;
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, SessionFactory sessionFactory) {
        this.studentDao = studentDao;
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public Long createStudent(String name, String email) {
        Student s = new Student();
        s.setName(name);
        s.setEmail(email);
        s.setFeeBalance(BigDecimal.ZERO);
        return studentDao.save(s);
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        studentDao.update(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudent(Long id) {
        return studentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listStudents() {
        return studentDao.findAll();
    }

    @Override
    @Transactional
    public void payFees(Long studentId, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Student s = studentDao.findById(studentId);
        if (s == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }
        BigDecimal newBalance = s.getFeeBalance().subtract(amount);
        s.setFeeBalance(newBalance);
        studentDao.update(s);

        FeeTransaction tx = new FeeTransaction();
        tx.setStudent(s);
        tx.setAmount(amount);
        tx.setType(FeeTransaction.Type.PAYMENT);
        currentSession().save(tx);
    }

    @Override
    @Transactional
    public void refundFees(Long studentId, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Student s = studentDao.findById(studentId);
        if (s == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }
        BigDecimal newBalance = s.getFeeBalance().add(amount);
        s.setFeeBalance(newBalance);
        studentDao.update(s);

        FeeTransaction tx = new FeeTransaction();
        tx.setStudent(s);
        tx.setAmount(amount);
        tx.setType(FeeTransaction.Type.REFUND);
        currentSession().save(tx);
    }
}


