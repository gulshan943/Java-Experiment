package com.example.di.config;

import com.example.di.service.MessageService;
import com.example.di.service.impl.EmailMessageService;
import com.example.di.consumer.NotificationSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MessageService messageService() {
        return new EmailMessageService();
    }

    @Bean
    public NotificationSender notificationSender(MessageService messageService) {
        return new NotificationSender(messageService);
    }
}


