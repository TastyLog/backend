package com.example.foodlog.domain.notification.service;

import com.example.foodlog.domain.notification.dto.FCMMessageRequestDto;

import java.io.IOException;

public interface NotificationService {

    void sendMessageTo(String FCMToken,FCMMessageRequestDto fcmMessageRequestDto) throws IOException;
}
