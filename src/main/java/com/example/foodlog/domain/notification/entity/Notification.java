package com.example.foodlog.domain.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private String title;
    private String body;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    private String token;

    @Builder
    public Notification(String title, String token,String body) {
        this.title = title;
        this.token = token;
        this.body  = body;
    }

    //    public void confirmUser(User user) {
//        this.user = user;
//    }
}
