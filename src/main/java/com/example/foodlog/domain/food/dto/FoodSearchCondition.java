package com.example.foodlog.domain.food.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodSearchCondition {
    private List<Long> youtuberId;
    private String searchWord;
}
