package com.example.foodlog.domain.food.entity;

import com.example.foodlog.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Youtuber extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "YOUTUBER_ID")
    private Long id;
    /**
     * ChannelId
     */
    private String channelId;

    /**
     * 유투버 프로필
     */
    @Column(length = 300,nullable = false, unique = true)
    private String youtuberProfile;

    /**
     * 유투버 이름
     */
    @Column(length = 300,nullable = false,unique = true)
    private String youtuberName;

    @Builder.Default
    @OneToMany(mappedBy = "youtuber", cascade = CascadeType.ALL)
    private List<Food> foodArrayList=new ArrayList<>();

}
