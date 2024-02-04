package com.example.foodlog.domain.notification.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FCMMessageRequestDto {
    private String title;
    private String body;
    private String userId;
    private String isEnd;
}
