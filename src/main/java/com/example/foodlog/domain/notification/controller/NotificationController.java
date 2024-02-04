package com.example.foodlog.domain.notification.controller;


import com.example.foodlog.domain.notification.dto.FCMMessageRequestDto;
import com.example.foodlog.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping("/send/{FCMToken}")
    public void notiifcationTest(@PathVariable(name="FCMToken") String FCMToken,@RequestBody FCMMessageRequestDto fcmMessageRequestDto) throws IOException {
        notificationService.sendMessageTo(FCMToken,fcmMessageRequestDto);
        log.info("알림전송");
    }
}
