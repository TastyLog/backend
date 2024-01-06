package com.example.foodlog.domain.food.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodSearchCondition {
    private Long youtuberId;
}
