package com.example.foodlog.domain.notification.service.impl;

import com.example.foodlog.domain.notification.dto.FCMMessageFormatDto;
import com.example.foodlog.domain.notification.dto.FCMMessageRequestDto;
import com.example.foodlog.domain.notification.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;


@Service
@Slf4j
public class FcmService implements NotificationService {


    @Value("${fcm.key.path}")
    private String firebaseJson;
    @Value("${fcm.key.scope}")
    private String firebaseScope;


    private final ObjectMapper objectMapper;

    @Autowired
    public FcmService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }



    /**
     * 알림 푸쉬를 보내는 역할을 하는 메서드
      푸쉬 알림을 받을 클라이언트 앱의 식별 토큰
     * */
    public void sendMessageTo(String FCMToken,FCMMessageRequestDto fcmMessageRequestDto) throws IOException{

        final String title=fcmMessageRequestDto.getTitle();
        final String body=fcmMessageRequestDto.getBody();
        final String id=fcmMessageRequestDto.getUserId();
        final String isEnd=fcmMessageRequestDto.getIsEnd();
        String message = makeMessage(FCMToken, title, body, id, isEnd);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(firebaseScope)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        log.info(response.body().string());
    }

    /**
     * makeMessage : 알림 파라미터들을 FCM이 요구하는 body 형태로 가공한다.
     * @param targetToken : firebase token
     * @param title : 알림 제목
     * @param body : 알림 내용
     * @return
     * */
    private String makeMessage(String targetToken, String title, String body, String name, String description) throws JsonProcessingException {
        FCMMessageFormatDto fcmMessage = FCMMessageFormatDto.builder()
                .message(
                        FCMMessageFormatDto.Message.builder()
                                .token(targetToken)
                                .notification(
                                        FCMMessageFormatDto.Notification.builder()
                                                .title(title)
                                                .body(body)
                                                .build()
                                )
                                .data(
                                        FCMMessageFormatDto.Data.builder()
                                                .name(name)
                                                .description(description)
                                                .build()
                                )
                                .build()
                )
                .validateOnly(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);

    }

    private String getAccessToken() throws IOException {
        // firebase로 부터 access token을 가져온다.

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseJson).getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();
    }


}