package com.example.di.service.impl;

import com.example.di.service.MessageService;

public class EmailMessageService implements MessageService {
    @Override
    public String sendMessage(String recipient, String messageBody) {
        return "Email sent to " + recipient + ": " + messageBody;
    }
}


