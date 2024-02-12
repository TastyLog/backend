package com.example.foodlog.domain.food.entity;


import com.example.foodlog.global.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

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
    private String rating;

    /**
     * 대표이미지
     */
    @Column(length = 300,nullable = false)
    private String reprsentativeImage;

    @JoinColumn(name = "YOUTUBER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Youtuber youtuber;

//    private Point location;

    @Builder
    public Food(String restaurant, String category, String phoneNumber, String address, String youtubeLink, String latitude, String longtitude, String naverLink, Integer totalReview, String rating, String reprsentativeImage, Youtuber youtuber) {
        this.restaurant = restaurant;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.youtubeLink = youtubeLink;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.naverLink = naverLink;
        this.totalReview = totalReview;
        this.rating = rating;
        this.reprsentativeImage = reprsentativeImage;
        this.youtuber = youtuber;
    }

    public void createFood(Food food) {
        this.restaurant = food.getRestaurant();
        this.category = food.getCategory();
        this.phoneNumber = food.getPhoneNumber();
        this.address = food.getAddress();
        this.youtubeLink = food.getYoutubeLink();
        this.latitude = food.getLatitude();
        this.longtitude = food.getLongtitude();
        this.naverLink = food.getNaverLink();
        this.totalReview = food.getTotalReview();
        this.rating = food.getRating();
        this.reprsentativeImage = food.getReprsentativeImage();
        this.youtuber = food.getYoutuber();
    }
}
