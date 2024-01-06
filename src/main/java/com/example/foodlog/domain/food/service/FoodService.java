package com.example.foodlog.domain.food.service;

import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {

    Page<ReadFoodListResponseDto> readAllList(String latitude, String longitude, Pageable pageable);
}
