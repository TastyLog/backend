package com.example.foodlog.domain.food.entity;


import com.example.foodlog.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Food extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ID")
    private Long id;

    /**
     * 유튜버 명/음식점 명으로 중복값 방지
     */
    @Column(nullable = false, unique = true)
    private String uniqueName;

    /**
     * 음식점 명
     */
    @Column(length = 300,nullable = false)
    private String restaurant;

    /**
     * 카테고리
     */
    @Column(length = 300,nullable = false)
    private String category;

    /**
     * 음식점 전화번호
     */
    @Column(length = 300,nullable = false)
    private String phoneNumber;

    /**
     * 음식점 주소
     */
    @Column(length = 300,nullable = false)
    private String address;

    /**
     * 유투버프로필
     */
    @Column(length = 300,nullable = false)
    private String youtuberProfile;

    /**
     * 유투버 링크
     */
    @Column(length = 300,nullable = false)
    private String youtuberName;

    /**
     * 유튜브 링크
     */
    @Column(length = 300,nullable = false)
    private String youtubeLink;

    /**
     * 위도
     */
    @Column(length = 300,nullable = false)
    private String latitude;

    /**
     * 경도
     */

    @Column(length = 300,nullable = false)
    private String longtitude;

    /**
     * 네이버 링크
     */
    @Column(length = 300,nullable = false)
    private String naverLink;
    /**
     * 리뷰 수
     */

    @Column(length = 300,nullable = false)
    private Integer totalReview;

    /**
     * 별점
     */
    @Column(length = 300,nullable = false)
    private double rating;

    /**
     * 대표이미지
     */
    @Column(length = 300,nullable = false)
    private String reprsentativeImage;

}
