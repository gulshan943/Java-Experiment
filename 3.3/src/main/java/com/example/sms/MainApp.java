package com.example.sms;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.sms.config.AppConfig;
import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            StudentService service = ctx.getBean(StudentService.class);

            Long aliceId = service.createStudent("Alice Walker", "alice@example.com");
            Long bobId = service.createStudent("Bob Stone", "bob@example.com");

            log.info("Students after creation:");
            service.listStudents().forEach(s -> log.info(formatStudent(s)));

            service.payFees(aliceId, new BigDecimal("500.00"));
            service.refundFees(aliceId, new BigDecimal("100.00"));
            service.payFees(bobId, new BigDecimal("300.00"));

            log.info("Students after transactions:");
            service.listStudents().forEach(s -> log.info(formatStudent(s)));

            Student alice = service.getStudent(aliceId);
            alice.setName("Alice Johnson");
            service.updateStudent(alice);

            log.info("After update:");
            service.listStudents().forEach(s -> log.info(formatStudent(s)));

            service.deleteStudent(bobId);
            log.info("After deleting Bob:");
            service.listStudents().forEach(s -> log.info(formatStudent(s)));
        }
    }

    private static String formatStudent(Student s) {
        return "Student{" +
                "id=" + s.getId() +
                ", name='" + s.getName() + '\'' +
                ", email='" + s.getEmail() + '\'' +
                ", feeBalance=" + s.getFeeBalance() +
                '}';
    }
}


