package com.example.di.consumer;

import com.example.di.service.MessageService;

public class NotificationSender {
    private final MessageService messageService;

    public NotificationSender(MessageService messageService) {
        this.messageService = messageService;
    }

    public String notifyUser(String userEmail, String message) {
        return messageService.sendMessage(userEmail, message);
    }
}


