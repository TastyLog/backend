package com.example.foodlog.domain.food.dto.response;

import com.example.foodlog.domain.food.entity.Food;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ReadFoodListResponseDto {

    private String uniqueKey;
    private String restaurantName;
    private String category;
    private String phoneNumber;
    private String address;
    private String youtuberProfile;
    private String youtuberName;
    private String youtuberLink;
    private String latitude;
    private String longitude;
    private String naverLink;
    private Integer totalReviews;
    private String rating;
    private String representativeImage;
    private String distance;

    @Builder
    public ReadFoodListResponseDto(Food food,double  distance) {
        this.uniqueKey = food.getYoutuber()+"/"+food.getRestaurant();
        this.restaurantName=food.getRestaurant();
        this.category=food.getCategory();
        this.phoneNumber=food.getPhoneNumber();
        this.address=food.getAddress();
        this.youtuberProfile=food.getYoutuber().getYoutuberProfile();
        this.youtuberName=food.getYoutuber().getYoutuberName();
        this.youtuberLink=food.getYoutubeLink();
        this.latitude=food.getLatitude();
        this.longitude=food.getLongtitude();
        this.naverLink=food.getNaverLink();
        this.totalReviews=food.getTotalReview();
        this.rating=food.getRating();
        this.representativeImage=food.getReprsentativeImage();
        if(1000<=distance){
            distance=distance/1000;
            distance = Math.round(distance * 100.0) / 100.0;
            this.distance=distance+"KM";
        }else{
            this.distance=distance+"M";
        }
    }
}
