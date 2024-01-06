package com.example.foodlog.domain.food.service;

import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.dto.response.ReadYoutuberListResponseDto;

import java.util.List;

public interface YoutuberService {
    List<ReadYoutuberListResponseDto> readAllList();
}
