package com.example.di;

import com.example.di.config.AppConfig;
import com.example.di.consumer.NotificationSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        NotificationSender sender = context.getBean(NotificationSender.class);
        String result = sender.notifyUser("user@example.com", "Welcome to Spring DI with Java config!");
        System.out.println(result);
    }
}


